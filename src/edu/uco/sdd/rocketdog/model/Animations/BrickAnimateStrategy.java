package edu.uco.sdd.rocketdog.model.Animations;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.AbstractSpitzAnimationStrategy;
import javafx.geometry.Rectangle2D;

public class BrickAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public BrickAnimateStrategy() {
        super("/spritesheet.png", new Rectangle2D[]{
            new Rectangle2D(385, 333, 64, 64)
        });
    }

}
