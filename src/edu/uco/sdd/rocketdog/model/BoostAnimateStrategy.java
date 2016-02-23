package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;

public class BoostAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public BoostAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(34, 188, 57, 53),
            new Rectangle2D(90, 188, 57, 53),
            new Rectangle2D(149, 187, 57, 53),
            new Rectangle2D(204, 185, 57, 53),
            new Rectangle2D(256, 184, 57, 53),
            new Rectangle2D(296, 184, 57, 53),
            new Rectangle2D(256, 184, 57, 53),
            new Rectangle2D(204, 185, 57, 53),
            new Rectangle2D(149, 187, 57, 53),
            new Rectangle2D(90, 188, 57, 53),
        });
    }

}
