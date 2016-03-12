package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class BoneAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public BoneAnimateStrategy() {
        super("/healthbonesprite.png", new Rectangle2D[]{
            new Rectangle2D(9, 4, 56, 56),
            new Rectangle2D(75.5, 3, 56, 56),
            new Rectangle2D(132, 3, 56, 56),
            new Rectangle2D(182.5, 3, 56, 56),
            new Rectangle2D(228, 3, 50, 56),
            new Rectangle2D(269.5, 3, 56, 56),
            new Rectangle2D(319, 3, 50, 56),
            new Rectangle2D(366.5, 2, 56, 56),
            new Rectangle2D(425, 2, 56, 56),
            new Rectangle2D(425, 2, 56, 56),
            new Rectangle2D(425, 2, 56, 56),
            new Rectangle2D(366.5, 2, 56, 56),
            new Rectangle2D(319, 3, 50, 56),
            new Rectangle2D(269.5, 3, 56, 56),
            new Rectangle2D(228, 3, 50, 56),
            new Rectangle2D(182.5, 3, 56, 56),
            new Rectangle2D(132, 3, 56, 56),
            new Rectangle2D(75.5, 3, 56, 56),
            new Rectangle2D(9, 4, 56, 56),
            new Rectangle2D(9, 4, 56, 56)
        });
    }

}
