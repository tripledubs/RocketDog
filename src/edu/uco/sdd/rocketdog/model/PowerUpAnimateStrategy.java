package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;

public class PowerUpAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public PowerUpAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(33, 266, 56, 56),
            new Rectangle2D(99, 267, 56, 56),
            new Rectangle2D(158.5, 267, 56, 56),
            new Rectangle2D(220, 266, 56, 56),
            new Rectangle2D(276.5, 266, 50, 56),
            new Rectangle2D(351, 265, 56, 56),
            new Rectangle2D(276.5, 266, 50, 56),
            new Rectangle2D(220, 266, 56, 56),
            new Rectangle2D(158.5, 267, 56, 56),
            new Rectangle2D(99, 267, 56, 56)
        });
    }

}
