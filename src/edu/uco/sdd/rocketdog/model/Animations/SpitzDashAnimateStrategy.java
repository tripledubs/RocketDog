package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class SpitzDashAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public SpitzDashAnimateStrategy() {
        super("/Spitzer_Dash.png", new Rectangle2D[]{
            new Rectangle2D(44, 39, 353, 117),
            new Rectangle2D(361, 40, 353, 117),
            new Rectangle2D(700, 39, 353, 117)
        });
    }

}
