package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.commands.RocketDogController;
import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import edu.uco.sdd.rocketdog.model.Animations.SpitzIdleAnimateStrategy;
import edu.uco.sdd.rocketdog.view.Props;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Dubs
 */
public class LevelTwo extends Level {

    final public static int LEVEL_WIDTH = 3000; // Stage is 1000x924
    final public static int LEVEL_HEIGHT = 924;
    final public static int FOCAL_SPEED = 25;
    final public static int VIEWPORT_MAX_X = RocketDogGame.GAME_SCREEN_WIDTH / 2;
    final public static int VIEWPORT_MIN_X = 0;

    private final Group backgroundGroup;
    private final Group viewportGroup;
    private Boolean isDone;
    private RocketDog rocketdog;
    private RocketDogController gameController;

    UglyDog badguy;

    public LevelTwo(Group root, int width, int height, SoundManager soundManager) {
        super(root, width, height);

        root.setAutoSizeChildren(false);
        isDone = false;

        // Initialize Groups
        backgroundGroup = new Group();
        viewportGroup = new Group();

        // Initialize Rocketdog
        rocketdog = new RocketDog();
        rocketdog.setAnimation(new SpitzIdleAnimateStrategy());

        // Initialize sound
        soundManager.resetMediaPlayer(soundManager.getMp_bg(), "intense.mp3");
        soundManager.mp_bg.setVolume(0);
        soundManager.mp_bg.setCycleCount(100);
        soundManager.mp_am.setMute(true);

        // Initialize Viewport
        viewportGroup.getChildren().add(rocketdog.getSprite());

        // Initialize Background objects
        backgroundGroup.getChildren().add(Props.sunAndSky());
        backgroundGroup.getChildren().add(Props.sod(0, 2000, LEVEL_HEIGHT));
        backgroundGroup.getChildren().add(Props.house(0, LEVEL_HEIGHT - 300));
        backgroundGroup.getChildren().add(Props.house(LEVEL_WIDTH - 300, LEVEL_HEIGHT - 300));

        // Add Viewport + Background to root
        root.getChildren().add(backgroundGroup);
        root.getChildren().add(viewportGroup);

        badguy = new UglyDog("/Ugly Dog.png");
        badguy.setTranslateX(500);
        badguy.setTranslateY(500);

        backgroundGroup.getChildren().add(badguy);

        // All Commands go through gameController
        gameController = new RocketDogController(
                rocketdog, backgroundGroup, viewportGroup,
                FOCAL_SPEED, VIEWPORT_MIN_X, VIEWPORT_MAX_X, LEVEL_WIDTH, LEVEL_HEIGHT
        );

        // Set up key controller
        this.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    gameController.moveLeftButton();
                    break;
                case RIGHT:
                    gameController.moveRightButton();
                    break;
                case UP:
                    gameController.moveUpButton();
                    break;
                case DOWN:
                    gameController.moveDownButton();
                    break;
                case SPACE:
                    gameController.shootButton(backgroundGroup);
                    break;
            }
        });
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    protected Bounds absoluteBounds(Node x) {
        return x.localToScene(x.getBoundsInLocal());
    }

    public boolean levelIntersect(Node x, Node y) {
        return absoluteBounds(x).intersects(absoluteBounds(y));
    }

    @Override
    public void levelUpdate() {
        rocketdog.update();
        for (Node x: backgroundGroup.getChildren()) {
            if (x instanceof Bullet) {
                x.setTranslateX(x.getTranslateX()+5);
                Bullet b = (Bullet) x;
                b.update();
                if (levelIntersect(b,badguy)) {
                    badguy.setTranslateY(badguy.getTranslateY()-5);

                }
            }
        }

        if (levelIntersect(rocketdog.getSprite(),badguy)) {
            badguy.setTranslateX(badguy.getTranslateX()+10);
        }
    }
}
