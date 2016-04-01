package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class DManWalkAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public DManWalkAnimateStrategy() {
        super("/deliveryspritesheet.png", new Rectangle2D[]{
            new Rectangle2D(4, 6, 383, 400),
            new Rectangle2D(430, 9, 365, 400),
            new Rectangle2D(846, 9, 365, 400),
            new Rectangle2D(1614, 10, 381, 400),
            new Rectangle2D(1233, 5, 366, 400),
            new Rectangle2D(846, 9, 365, 400)
        });
    }

}
