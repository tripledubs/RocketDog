package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;

public class BrickAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public BrickAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(385, 333, 64, 64)
        });
    }

}
