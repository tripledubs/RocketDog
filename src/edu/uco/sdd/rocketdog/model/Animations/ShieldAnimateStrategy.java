package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class ShieldAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public ShieldAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(11, 16, 153, 150),
            new Rectangle2D(176, 16, 153, 150),
            new Rectangle2D(335, 17, 153, 150),
            new Rectangle2D(176, 16, 153, 150)
        });
    }

}
