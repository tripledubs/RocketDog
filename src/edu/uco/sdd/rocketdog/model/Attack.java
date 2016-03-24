package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import javafx.geometry.Point2D;

public class Attack extends Modification {

    public Attack(double hitboxWidth, double hitboxHeight, IAnimateStrategy animation) {
        super(new Point2D(0, 0), hitboxWidth, hitboxHeight, animation);
        super.setCurrentHealth(0);
    }

}
