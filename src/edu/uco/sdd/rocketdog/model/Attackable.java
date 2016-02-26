package edu.uco.sdd.rocketdog.model;

/**
 * Identifies an object that can be damaged.
 *
 * @author Spencer Harris
 */
public interface Attackable {

    void damage(double attackStrength);
}
