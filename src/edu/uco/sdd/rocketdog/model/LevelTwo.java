package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import edu.uco.sdd.rocketdog.view.Props;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Dubs
 */
public class LevelTwo extends Level {

    final public static int LEVEL_WIDTH = 3000; // Stage is 1000x924
    final public static int LEVEL_HEIGHT = 924;
    final public static int FOCAL_SPEED = 15;
    final public static int VIEWPORT_MAX_X = RocketDogGame.GAME_SCREEN_WIDTH / 2;
    final public static int VIEWPORT_MIN_X = 0;

    Group backgroundGroup;
    Group viewportGroup;
    Text viewportCoordinates;
    Rectangle viewportSquare;
    Rectangle blackSquare;
    Boolean isDone;
    RocketDog rocketdog;

    public LevelTwo(Group root, int width, int height) {
        super(root, width, height);
        root.setAutoSizeChildren(false);
        isDone = false;

        // Initialize Groups
        backgroundGroup = new Group();
        viewportGroup = new Group();

        // Initialize Viewport
        rocketdog = new RocketDog();
        viewportGroup.getChildren().add(rocketdog.getSprite());

        // Initialize Background objects
        backgroundGroup.getChildren().add(Props.sod(0, 2000, LEVEL_HEIGHT));
        backgroundGroup.getChildren().add(Props.house(0, LEVEL_HEIGHT - 300));
        backgroundGroup.getChildren().add(Props.house(LEVEL_WIDTH - 300, LEVEL_HEIGHT - 300));

        // Add Viewport + Background to root
        root.getChildren().add(viewportGroup);
        root.getChildren().add(backgroundGroup);

        this.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    moveLeft();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case UP:
                    moveUp();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case SPACE:
                    shoot();
                    break;
            }
        });
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void levelUpdate() {
        rocketdog.update();

    }

    /**
     * Returns the absolute Bound positioning of the Node in the Scene
     *
     * @param x Node
     * @return Bounds
     */
    public Bounds absoluteBounds(Node x) {
        return x.localToScene(x.getBoundsInLocal());
    }

    /**
     * Returns true if the bounds of two nodes intersect in the Scene
     *
     * @param x
     * @param y
     * @return
     */
    public boolean levelIntersect(Node x, Node y) {
        return absoluteBounds(x).intersects(absoluteBounds(y));
    }

    /**
     * Moves Rocketdog to the right in the viewport. If Rocketdog is past the
     * 1/2 mark, move the background left
     */
    private void moveRight() {
        Bounds rocketdogBounds = absoluteBounds(rocketdog.getSprite());
        ImageView rocketdogSprite = rocketdog.getSprite();
        double maxX = rocketdogBounds.getMaxX();
        double minX = rocketdogBounds.getMinX();
        double y = rocketdogBounds.getMinY();

        // Do not move right of level_WIDTH
        if (backgroundGroup.getTranslateX() - RocketDogGame.GAME_SCREEN_WIDTH < -LEVEL_WIDTH) {
            return;
        // If rocketdog would go right of the viewport, move background left
        } else if (maxX + FOCAL_SPEED > VIEWPORT_MAX_X) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() - FOCAL_SPEED);
            // Otherwise, just move the circle to the right ie move within the viewport
        } else {
            rocketdogSprite.setTranslateX(rocketdogSprite.getTranslateX() + FOCAL_SPEED);
        }
    }

    private void moveLeft() {
        Bounds rocketdogBounds = absoluteBounds(rocketdog.getSprite());
        ImageView rocketdogSprite = rocketdog.getSprite();
        double maxX = rocketdogBounds.getMaxX();
        double minX = rocketdogBounds.getMinX();
        double y = rocketdogBounds.getMinY();

        // Background cannot go above 0 or else moving left of the level
        if (backgroundGroup.getTranslateX() + FOCAL_SPEED > 0 && minX < 10) {
            return;
        } // If rd would go right of the viewport, move background left
        else if (minX - FOCAL_SPEED < VIEWPORT_MIN_X) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() + FOCAL_SPEED);
            // Otherwise, just move the circle to the right ie move within the viewport
        } else {
            rocketdogSprite.setTranslateX(rocketdogSprite.getTranslateX() - FOCAL_SPEED);
        }
    }

    private void shoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveUp() {
        rocketdog.getSprite().setTranslateY(rocketdog.getSprite().getTranslateY() - FOCAL_SPEED);
    }

    private void moveDown() {
        rocketdog.getSprite().setTranslateY(rocketdog.getSprite().getTranslateY() + FOCAL_SPEED);
    }
}
