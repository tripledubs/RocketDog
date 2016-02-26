package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class SpitzChargeGunAnimateStrategy extends AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    public SpitzChargeGunAnimateStrategy() {
        super("/Spitzer_Charge.png", new Rectangle2D[]{
            new Rectangle2D(29, 28, 147, 141),
            new Rectangle2D(208, 28, 147, 141),
            new Rectangle2D(387, 28, 147, 141),
            new Rectangle2D(560, 28, 147, 141),
            new Rectangle2D(736, 28, 147, 141),
            new Rectangle2D(915, 28, 147, 141)
        });

    }

}
