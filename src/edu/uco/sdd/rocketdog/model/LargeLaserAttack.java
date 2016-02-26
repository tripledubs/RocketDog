package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzLargeLaserWeaponAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzLaserWeaponAnimateStrategy;
import javafx.geometry.Point2D;

public class LargeLaserAttack extends Attack {

    public LargeLaserAttack(Point2D position, Point2D velocity, boolean isActive) {
        super(44, 44, new SpitzLargeLaserWeaponAnimateStrategy());
        super.setPosition(position);
        super.setVelocity(velocity);
        super.setActive(isActive);
    }

}
