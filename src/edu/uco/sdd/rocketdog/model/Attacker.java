package edu.uco.sdd.rocketdog.model;

/**
 * This interface describes entities that can attack.
 * @author Spencer Harris
 */
public interface Attacker {
  void attack(TangibleEntity target);
}
