package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public interface IAnimateStrategy {

    public void handle();

    public Rectangle2D getCurrentView();

    public Image getImage();
}
