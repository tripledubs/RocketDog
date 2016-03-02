package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.KeyMappingContext;
import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import edu.uco.sdd.rocketdog.view.Props;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class LevelTwo extends Scene implements ILevel {

    public static final double LEVEL_WIDTH = 8000;
    public static final double LEVEL_HEIGHT = 600;
    
    AbstractMap<Group, Double> backgrounds; // Group followed by speed of scroll
    Group levelItems = new Group(); // Everything besides RocketDog and Backgrounds
    
    private KeyMappingContext keyMapping;
    
    private RocketDog rocketdog;

    public LevelTwo(Group root, double width, double height) {
        super(root, width, height);
        root.setAutoSizeChildren(false);
        backgrounds = new LinkedHashMap<>(); // Linked hash map keeps iteration order

        rocketdog = new RocketDog();
        ImageView rocketdogSprite = rocketdog.getSprite();
        rocketdog.getSprite().setId("Rocketdog");

        makeBackgrounds(); // Fills background with content

        Enemy e = new Enemy.Builder("/Ugly Dog.png", 24, 24)
                .setX(700)
                .setY(500)
                .build();

        Enemy e1 = new Enemy.Builder("/Ugly Dog.png", 24, 24)
                .setX(1200)
                .setY(500)
                .build();

        this.setOnKeyPressed((KeyEvent event) -> {
            double rddx = rocketdog.getVelocity().getX();
            double rddy = rocketdog.getVelocity().getY();
            switch (event.getCode()) {
                case LEFT:
                    rocketdog.setVel(-2, rddy);
                    break;
                case RIGHT:
                    rocketdog.setVel(4, rddy);
                    break;
                case DOWN:
                    rocketdog.setVel(rddx, 1);
                    break;
                case UP:
                    rocketdog.setVel(rddx, -1);
                    break;
                case SPACE:
                    rocketdog.setVel(0,0);
                    break;
             }
        });

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
        root.getChildren().add(rocketdog.getSprite());
    }

    public void positionScreen() {
        boolean screenMoving = false;
        double width = RocketDogGame.GAME_SCREEN_WIDTH;
        double height = RocketDogGame.GAME_SCREEN_HEIGHT;

        double rdx = rocketdog.getSprite().getBoundsInParent().getMinX();
        double rdy = rocketdog.getPosition().getY();

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
            rocketdog.setPos(zoneWidth[2] + 1, rdy);
        }

        if (rdx < zoneWidth[1]) {
            scrollLeft();

        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void levelUpdate() {
        rocketdog.update();
        positionScreen();

    }

    private void scrollRight() {
        double rddx = rocketdog.getVelocity().getX();
        double rddy = rocketdog.getVelocity().getY();

        if (rddx < 1) {
            return;
        }

        double normal = rddx;
        backgrounds.forEach((group, scrollSpeed) -> {
            // Limit Scroll Speed to the velocity of RocketDog
            if (scrollSpeed > rddx) {
                scrollSpeed = rddx;
            }
            group.setTranslateX(group.getTranslateX() - scrollSpeed);
        });
        levelItems.setTranslateX(levelItems.getTranslateX() - rddx);

    }

    private void stopScrolling() {
        rocketdog.setVel(0, 0);
    }

    private void scrollLeft() {
        int fast = -3;
        int medium = -2;
        int slow = -1;

    }

    private void makeBackgrounds() {
        Group bgSlow = new Group();
        bgSlow.getChildren().add(Props.sunAndSky());
        bgSlow.getChildren().add(Props.sod(0, 1200, 600));

        Group bgMedium = new Group();
        bgMedium.getChildren().add(Props.sod(5, 1200, 600));

        Group bgFast = new Group();
        bgFast.getChildren().add(Props.sod(0, 1200, 600));

        

        backgrounds.put(bgSlow, .005);
        backgrounds.put(bgMedium, .08);
        backgrounds.put(bgFast, .1);

    }

}
