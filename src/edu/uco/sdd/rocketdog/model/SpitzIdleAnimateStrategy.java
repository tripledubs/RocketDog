package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Rectangle2D;

public class SpitzIdleAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public SpitzIdleAnimateStrategy() {
        super("/Spitzer_Idle.png", new Rectangle2D[]{
            new Rectangle2D(220, 31, 132, 140),
            new Rectangle2D(399, 31, 131, 140),
            new Rectangle2D(572, 31, 135, 140),
            new Rectangle2D(748, 31, 132, 140),
            new Rectangle2D(748, 31, 132, 140),
            new Rectangle2D(927, 31, 131, 140)
        });
    }

}
