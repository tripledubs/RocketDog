/**
 * Intent of this class is to produce useful nodes for levels. Could be
 * refactored later to a factory or abstract factory. Every prop should call
 * setID
 */
package edu.uco.sdd.rocketdog.view;

import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import static edu.uco.sdd.rocketdog.model.LevelTwo.LEVEL_HEIGHT;
import static edu.uco.sdd.rocketdog.model.LevelTwo.LEVEL_WIDTH;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Props {
    private static ImageViewLoader imgLoader;
    
    public Props() {
        imgLoader = ImageViewLoader.getInstance();
    }

    /**
     * Constructs a beautiful Sun and sky, arcs don't work yet. Pilfered from
     * https://blogs.oracle.com/wayneyoung/entry/side_scroller_in_javafx_2
     * @return Group
     */
    public static Group sunAndSky() {
        Group sunAndSky = new Group();
        // https://blogs.oracle.com/wayneyoung/entry/side_scroller_in_javafx_2
        Rectangle sky = new Rectangle(LEVEL_WIDTH, LEVEL_HEIGHT);
        sky.setFill(
                new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.LIGHTBLUE),
                        new Stop(0.7, Color.LIGHTYELLOW),
                        new Stop(1.0, Color.YELLOW))
        );
        double SUNX = 100;
        double SUNY = 100;
        sunAndSky.getChildren().add(sky);
        Circle sunCircle = new Circle(SUNX, SUNY, 60);
        sunCircle.setFill(Color.YELLOW);
        sunAndSky.getChildren().add(sunCircle);
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
            sunAndSky.getChildren().add(b);
        }
        sunAndSky.setId("Sun and Sky");
        return sunAndSky;
    }
    
    /**
     * Constructs green lines meant to look like grass
     * @param from the x coordinate to start at
     * @param to the x coordinate to finish at
     * @param height y coordinate to end at, usually levelHeight
     * @return Group
     */

    public static Group sod(int from, int to, double height) {
        assert(to > from);
        Group grass = new Group();
        for (int i = from; i < to; i += 5) {
            Line grassBlade = new Line();

            double random = Math.random() % 100 + 4;
            grassBlade.setStartY(height - (height / random));
            grassBlade.setEndY(height);
            grassBlade.setStartX(i + random);
            grassBlade.setEndX(i + random);
            
            grassBlade.setStrokeWidth(1.0);
            grassBlade.setStroke(Color.GREEN);
            grass.getChildren().add(grassBlade);
        }
        grass.setId("Sod");
        return grass;
    }
    
    /**
     * A beautiful random house
     * @param x X coordinate
     * @param y Y coordinate 
     * @return Node
     */
    public static Node house(double x, double y) {
        double randomHouse = Math.random() * 10 % 6 + 1; // Integer between 1 and 6
        Image img = new Image("/Resources/houses/house" + (int) randomHouse + ".png");
        ImageView sprite = new ImageView(img);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setScaleX(.5);
        sprite.setScaleY(.5);
        sprite.setId("House");
        return sprite;
    }
}
