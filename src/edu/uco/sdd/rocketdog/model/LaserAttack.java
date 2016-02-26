package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.SpitzLaserWeaponAnimateStrategy;
import javafx.geometry.Point2D;

/* This class will later be edited to be a builder
   This will allow for several laser bullets on the screen at a time
   However we can restrict the total number on the screen at a time. 
 */
public class LaserAttack extends Attack {

    public LaserAttack(Point2D position, Point2D velocity, boolean isActive) {
        super(44, 44, new SpitzLaserWeaponAnimateStrategy());
        super.setPosition(position);
        super.setVelocity(velocity);
        super.setActive(isActive);
    }

}
