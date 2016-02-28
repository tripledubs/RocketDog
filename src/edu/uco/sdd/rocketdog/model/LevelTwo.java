package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;  // Dont leave these

/**
 *
 * @author Dubs
 */
public class LevelTwo extends Scene implements ILevel {

    Parent root;
    RocketDog rocketdog;
    double sceneWidth;
    double sceneHeight;
    Group background;
    Group characters;
    Group foreground;
    
    ArrayList<TangibleEntity> EntitiesToUpdate;

    public LevelTwo(Group root, double width, double height) {
        super(root, width, height);
        sceneWidth = width;
        sceneHeight = height;
        root.setAutoSizeChildren(false);

        background = new Group();
        characters = new Group();
        foreground = new Group();
        
        EntitiesToUpdate = new ArrayList<>();
        
        rocketdog = new RocketDog();
        EntitiesToUpdate.add(rocketdog);
        root.getChildren().addAll(makeBackground());
        root.getChildren().addAll(getCharacters());
//        root.getChildren().addAll(getForeground());

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void levelUpdate() {
        //rd.update();
        EntitiesToUpdate.forEach(te -> te.update());
    }

    private ArrayList<Node> makeBackground() {
        // https://blogs.oracle.com/wayneyoung/entry/side_scroller_in_javafx_2
        ArrayList<Node> nodes = new ArrayList<>();
        Rectangle sky = new Rectangle(sceneWidth, sceneHeight);
        sky.setFill(
                new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.LIGHTBLUE),
                        new Stop(0.7, Color.LIGHTYELLOW),
                        new Stop(1.0, Color.YELLOW))
        );
        double SUNX = 100;
        double SUNY = 100;
        Group sun = new Group();
        Circle sunCircle = new Circle(SUNX, SUNY, 60);
        sunCircle.setFill(Color.YELLOW);
        sun.getChildren().add(sunCircle);
        Arc arc = new Arc();
        arc.setCenterX(200);
        arc.setCenterY(200);
        arc.setRadiusX(500);
        arc.setRadiusY(500);
        /**
         * Following loop does not work yet
         */
        for (int i = 0; i < 12; i++) {
            Arc b = new Arc();
            b.setStartAngle(2 * i * (360 / 24));
            b.setLength(360 / 24);
            b.setType(ArcType.ROUND);
            b.setFill(Color.YELLOW);
            b.setOpacity(0.3);
            sun.getChildren().add(b);
        }
        nodes.add(sky);
        nodes.add(sun);
        return nodes;
    }

    private ArrayList<Node> getCharacters() {
        ArrayList<Node> chars = new ArrayList<>();
        rocketdog = new RocketDog();
        chars.add(rocketdog.getSprite());
        
        return chars;
    }

    private ArrayList<Node> getForeground() {
        return null;
    }

    public void positionScreen() {
        double width = RocketDogGame.GAME_SCREEN_WIDTH;
        double height = RocketDogGame.GAME_SCREEN_HEIGHT;

        double rdx = rocketdog.getPosition().getX();
        double rdy = rocketdog.getPosition().getY();
        
        double bgx = background.getTranslateX();
        double bgy = background.getTranslateY();

        /**
         * Divide the screen into 10 zones
         */
        int numZones = 10;
        double[] zoneWidth = new double[numZones];
        double[] zoneHeight = new double[numZones];
        for (int i = 0; i < numZones; i++) {
            zoneWidth[i] = i * (width / numZones);
            zoneHeight[i] = i * (height / numZones);
        }

        if (rdy >= zoneHeight[9]) {
            return;
        }

        if (rdx > zoneWidth[8]) {
            background.setTranslateX(bgx - 5);
            rocketdog.setPos(zoneWidth[8], rdy);
        }
        if (rdx > zoneWidth[4]) {
            background.setTranslateX(bgx - 1);
        }

        //RD goes left so BG goes right
        if (rdx < zoneWidth[0]) {
            background.setTranslateX(bgx + 1);
            rocketdog.setPos(zoneWidth[0], rdy);
        }

        if (rdx < zoneWidth[1]) {
            background.setTranslateX(bgx + 1);
        }

        if (rdy > zoneHeight[8]) {
            background.setTranslateY(bgy - 5);
            rocketdog.setPos(rdx, zoneHeight[8]);
        }

        // RD goes down so bg goes up
        if (rdy > zoneHeight[7]) {
            background.setTranslateY(bgy - 1);
        }

        // RD goes up so bg goes down
        if (rdy < zoneHeight[1]) {
            background.setTranslateY(bgy + 1);
        }
        if (rdy < zoneHeight[0]) {
            background.setTranslateY(bgy + 5);
            rocketdog.setPos(rdx, zoneHeight[0]);
        }
    }

}
