package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.KeyMappingContext;
import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import edu.uco.sdd.rocketdog.view.Props;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class LevelTwo extends Level{

    public static final int LEVEL_WIDTH = 8000;
    public static final int LEVEL_HEIGHT = 924;
    
    /**
     * This is the game play speed of the level. Background objects
     * should move slower than this, foreground objects should move
     * faster than this. Velocity should match 
     */
    public static final double focalSpeed = 4;
    
    AbstractMap<Group, Double> backgrounds; // Group followed by speed of scroll
    Group levelItems = new Group(); // Everything besides RocketDog and Backgrounds

    public LevelTwo(Group root, int width, int height) {
        super(root,width,height);
        root.setAutoSizeChildren(false);
        backgrounds = new LinkedHashMap<>(); // Linked hash map keeps iteration order

        makeBackgrounds(); // Fills background with content

        Enemy e = new Enemy.Builder("/Ugly Dog.png", 24, 24)
                .setX(700)
                .setY(500)
                .build();

        Enemy e1 = new Enemy.Builder("/Ugly Dog.png", 24, 24)
                .setX(1200)
                .setY(500)
                .build();



        // Add items to levelItems
        levelItems.getChildren().add(Props.house(300, 600 - 245));
        // Add houses every 300 pixels
        for (int i=250; i < LEVEL_WIDTH; i+=300) {
            levelItems.getChildren().add(Props.house(i,600-244));
        }

        for (int i = 200; i < LEVEL_WIDTH; i += 100) {
            int random = (int) (Math.random() % 200 *100);
            System.out.println("" + random);
            Enemy enemy = new Enemy.Builder("/Ugly Dog.png", 24, 24)
                    .setX(i + random)
                    .setY(500)
                    .build();
            enemy.getSprite().setScaleX(-1);
            levelItems.getChildren().add(enemy.getSprite());
        }
        levelItems.getChildren().addAll(e.getSprite(),e1.getSprite());


        // Add backgrounds to root
        backgrounds.forEach((k, v) -> {
            root.getChildren().add(k);
        });

        // Add Everything else
        root.getChildren().add(levelItems);
        // Add RocketDog to root
        root.getChildren().add(super.getRocketDog().getSprite());
    }

    public void positionScreen() {
        boolean screenMoving = false;
        double width = RocketDogGame.GAME_SCREEN_WIDTH;
        double height = RocketDogGame.GAME_SCREEN_HEIGHT;

        double rdx = super.getRocketDog().getSprite().getBoundsInParent().getMinX();
        double rdy = super.getRocketDog().getPosition().getY();

        /**
         * Divide the screen into 10 zones
         */
        int numZones = 5;
        double[] zoneWidth = new double[numZones];
        double[] zoneHeight = new double[numZones];
        for (int i = 0; i < numZones; i++) {
            zoneWidth[i] = i * (width / numZones);
            zoneHeight[i] = i * (height / numZones);
        }

        if (rdx > LEVEL_WIDTH) {
            stopScrolling();
        }

        if (rdx >= zoneWidth[2]) {
            scrollRight();
            super.getRocketDog().setPos(zoneWidth[2] + 1, rdy);
        }

        if (rdx < zoneWidth[0]) {
            scrollLeft();
            super.getRocketDog().setPos(zoneWidth[0] - 1, rdy);

        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void levelUpdate() {
        super.levelUpdate();
        positionScreen();

    }

    private void stopScrolling() {
        super.getRocketDog().setVel(0, 0);
    }

    private void scrollRight() {
        double rddx = super.getRocketDog().getVelocity().getX();
        double rddy = super.getRocketDog().getVelocity().getY();

        if (rddx < 1) {
            return;
        }

        backgrounds.forEach((group, scrollSpeed) -> {
            // Limit Scroll Speed to the velocity of RocketDog
            if (scrollSpeed > rddx) {
                scrollSpeed = rddx;
            }
            group.setTranslateX(group.getTranslateX() - scrollSpeed);
        });
        levelItems.setTranslateX(levelItems.getTranslateX() - rddx);
    }

    private void scrollLeft() {
        double rddx = super.getRocketDog().getVelocity().getX();
        backgrounds.forEach((group, scrollSpeed) -> {
            group.setTranslateX(group.getTranslateX() + scrollSpeed );
        });
        levelItems.setTranslateX(levelItems.getTranslateX() + Math.abs(rddx));

    }

    private void makeBackgrounds() {
        Group bgSlow = new Group();
        bgSlow.getChildren().add(Props.sunAndSky());
        bgSlow.getChildren().add(Props.sod(0, 1200, 600));

        Group bgMedium = new Group();
        bgMedium.getChildren().add(Props.sod(5, 1200, 600));

        Group bgFast = new Group();
        bgFast.getChildren().add(Props.sod(0, 12000, 600));

        backgrounds.put(bgSlow, .005);
        backgrounds.put(bgMedium, .08);
        backgrounds.put(bgFast, focalSpeed - 1);

    }

}
