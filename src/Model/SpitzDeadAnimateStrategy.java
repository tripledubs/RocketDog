package Model;

import javafx.geometry.Rectangle2D;

class SpitzDeadAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public SpitzDeadAnimateStrategy() {
        super("/Spitzer_Dead.png", new Rectangle2D[]{
            new Rectangle2D(34, 24, 142, 148),
            new Rectangle2D(214, 31, 142, 140),
            new Rectangle2D(391, 31, 142, 140),
            new Rectangle2D(575, 31, 142, 140),
            new Rectangle2D(751, 31, 142, 140),
            new Rectangle2D(930, 31, 142, 140)
        });
    }

}
