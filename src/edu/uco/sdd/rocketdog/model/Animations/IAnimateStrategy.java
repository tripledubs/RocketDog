package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public interface IAnimateStrategy {
    void handle();
    Rectangle2D getCurrentView();
    Image getImage();
}
