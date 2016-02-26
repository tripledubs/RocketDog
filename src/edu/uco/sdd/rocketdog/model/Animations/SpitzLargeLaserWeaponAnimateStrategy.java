package edu.uco.sdd.rocketdog.model.Animations;

import javafx.geometry.Rectangle2D;

public class SpitzLargeLaserWeaponAnimateStrategy extends AbstractSpitzAnimationStrategy {

    public SpitzLargeLaserWeaponAnimateStrategy() {
        super("/Shoot.png", new Rectangle2D[]{
            new Rectangle2D(20, 127, 202, 133),
            new Rectangle2D(277, 128, 201, 131),
            new Rectangle2D(527, 127, 203, 133),
            new Rectangle2D(785, 128, 198, 131)
        });
    }
}
