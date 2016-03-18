package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LevelTest extends Scene implements ILevel {
    
    public static double LEVEL_HEIGHT=924;
    public static double LEVEL_WIDTH=8000;
    
    Group backgroundGroup;
    Group viewPortGroup;
    SubScene viewPortSubScene;
    Text viewportCoordinates;
    Rectangle viewportSquare;
    Rectangle blackSquare;

    Circle whiteCircle;

    LevelTest(Group root, int width, int height) {
        super(root,width,height);

        // Initialization
        backgroundGroup = new Group();
        viewPortGroup = new Group();
        viewPortSubScene = new SubScene(viewPortGroup,1000,924);
        viewportCoordinates = new Text(0,100,"Hello");

        // Main Guy
        whiteCircle = new Circle(100,500,30);
        whiteCircle.setFill(Color.WHITE);


        // Black Square
        viewportSquare = new Rectangle(0,333,500,333);
        viewportSquare.setFill(Color.GREEN);
        viewportSquare.setOpacity(.1);

        // Add Nodes to viewPortGroup
        viewPortGroup.getChildren().add(viewportSquare);
        viewPortGroup.getChildren().add(viewportCoordinates);
        viewPortGroup.getChildren().add(new Text(10,333,"ViewPort"));
        viewPortGroup.getChildren().add(whiteCircle);


        blackSquare = new Rectangle(750,500,100,100);
        blackSquare.setFill(Color.BLACK);
        backgroundGroup.getChildren().add(blackSquare);
        backgroundGroup.getChildren().add(new Text(750,480,"Background"));

        
        root.getChildren().add(backgroundGroup);
        root.getChildren().add(viewPortSubScene);


        this.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    moveLeft();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case F2:
                    backgroundGroup.getChildren().add(blackSquare);
                    break;
                case F3:
                    viewPortGroup.getChildren().add(blackSquare);
            }
        });
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void levelUpdate() {
        viewportCoordinates.setText(
                "Black Square(bounds): " + blackSquare.localToScene(blackSquare.getBoundsInLocal()) + "\n" +
                "White Circle(bounds): " + whiteCircle.localToScene(whiteCircle.getBoundsInLocal()) + "\n" 
        );

        for (Node node : backgroundGroup.getChildren()) {
            Bounds nodeBounds = node.localToScene(node.getBoundsInLocal()); // Absolute Bounds
            if (nodeBounds.intersects(viewportSquare.localToScene(viewportSquare.getBoundsInLocal()))) {
                // In the Viewport
                viewportSquare.setFill(Color.RED);
            } else {
                viewportSquare.setFill(Color.GREEN);
            }
            if (nodeBounds.intersects(whiteCircle.localToScene(whiteCircle.getBoundsInLocal()))) {
                whiteCircle.setFill(Color.RED);
            } else {
                whiteCircle.setFill(Color.WHITE);
            }
        }

    }

    private void moveRight() {
        Point2D whiteCircleLocation = whiteCircle.localToScene(50,500);
        double x = whiteCircleLocation.getX();
        double y = whiteCircleLocation.getY();

        // If whitecircle would go right of the viewport, move background left
        if ( x + 5 > 250) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() - 5);
        // Otherwise, just move the circle to the right ie move within the viewport
        } else {
            whiteCircle.setTranslateX(whiteCircle.getTranslateX() + 5);
        }
    }

    private void moveLeft() {
        Point2D whiteCircleLocation = whiteCircle.localToScene(50,500);
        double x = whiteCircleLocation.getX();
        double y = whiteCircleLocation.getY();

        // If whitecircle would go left of the viewport, move background right
        if ( x - 5 < 0) {
            backgroundGroup.setTranslateX(backgroundGroup.getTranslateX() + 5);

        // Otherwise, just move the circle to the left ie move within the viewport
        } else {
            whiteCircle.setTranslateX(whiteCircle.getTranslateX() - 5);
        }
    }


}
