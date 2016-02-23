package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;

public class SpikeAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public SpikeAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(276, 336, 64, 64)
        });
    }

}
