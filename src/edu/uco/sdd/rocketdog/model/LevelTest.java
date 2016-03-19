package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LevelTest extends Scene implements ILevel {

    public static double LEVEL_HEIGHT = 924;
    public static double LEVEL_WIDTH = 8000;

    private double VIEWPORT_MIN_X = 0;
    private double VIEWPORT_MAX_X = 500;
    private double VIEWPORT_MIN_Y = 333;
    private double VIEWPORT_MAX_Y = 666;

    Group backgroundGroup;
    Group viewPortGroup;
    Text viewportCoordinates;
    Rectangle viewportSquare;
    Rectangle blackSquare;

    Circle whiteCircle;

    LevelTest(Group root, int width, int height) {
        super(root, width, height);

        // Initialize Groups
        backgroundGroup = new Group();
        viewPortGroup = new Group();
        
        root.getChildren().add(backgroundGroup);
        root.getChildren().add(viewPortGroup);

        // Main Guy
        whiteCircle = new Circle(100, 500, 30);

        // Black Square
        viewportSquare = new Rectangle(
                VIEWPORT_MIN_X,
                VIEWPORT_MIN_Y, 
                VIEWPORT_MAX_X - VIEWPORT_MIN_X, // WIDTH
                VIEWPORT_MAX_Y - VIEWPORT_MIN_Y  // HEIGHT
        );
        viewportSquare.setOpacity(.25); // 0 = Fully transparent

        // Add Nodes to viewPortGroup
        viewPortGroup.getChildren().add(viewportSquare);
        viewportCoordinates = new Text(0, 100, "Hello");
        viewPortGroup.getChildren().add(viewportCoordinates);
        viewPortGroup.getChildren().add(new Text(10, 333, "ViewPort"));
        viewPortGroup.getChildren().add(whiteCircle);

        // Add nodes to background Group
        blackSquare = new Rectangle(750, 500, 100, 100);
        blackSquare.setFill(Color.BLACK);
        backgroundGroup.getChildren().add(blackSquare);
        backgroundGroup.getChildren().add(new Text(750, 480, "Background"));

        this.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    moveLeft();
                    break;
                case RIGHT:
                    moveRight();
                    break;
            }
        });
    }

    @Override
    public boolean isDone() {
        return false;
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

    @Override
    public void levelUpdate() {
        viewportCoordinates.setText(
                "Black Square(bounds): " + blackSquare.localToScene(blackSquare.getBoundsInLocal()) + "\n"
                + "White Circle(bounds): " + whiteCircle.localToScene(whiteCircle.getBoundsInLocal()) + "\n"
        );

        // Example collision detection
        for (Node node : backgroundGroup.getChildren()) {
            if (levelIntersect(node, viewportSquare)) {
                viewportSquare.setFill(Color.RED);
            } else {
                viewportSquare.setFill(Color.GREEN);
            }
            if (levelIntersect(node, whiteCircle)) {
                whiteCircle.setFill(Color.RED);
            } else {
                whiteCircle.setFill(Color.BLACK);
            }
        }
    }

    private void moveRight() {
        Point2D whiteCircleLocation = whiteCircle.localToScene(50, 500);
        double x = whiteCircleLocation.getX();
        double y = whiteCircleLocation.getY();

        // If whitecircle would go right of the viewport, move background left
        if (x + 5 > 250) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() - 5);
            // Otherwise, just move the circle to the right ie move within the viewport
        } else {
            whiteCircle.setTranslateX(whiteCircle.getTranslateX() + 5);
        }
    }

    private void moveLeft() {
        Point2D whiteCircleLocation = whiteCircle.localToScene(50, 500);
        double x = whiteCircleLocation.getX();
        double y = whiteCircleLocation.getY();

        // If whitecircle would go left of the viewport, move background right
        if (x - 5 < 0) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() + 5);
            // Otherwise, just move the circle to the left ie move within the viewport
        } else {
            whiteCircle.setTranslateX(whiteCircle.getTranslateX() - 5);
        }
    }
}
