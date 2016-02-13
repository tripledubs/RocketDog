package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

interface IAnimateStrategy {
    void handle();
    Rectangle2D getCurrentView();
    Image getImage();
}
