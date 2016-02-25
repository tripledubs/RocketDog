package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Attackable;
import edu.uco.sdd.rocketdog.model.TangibleEntity;

/**
 * Attack controller for entities that fire projectiles. A new entity is
 * created that has a MeleeAttackController.
 * @author Spencer Harris
 */
public class ProjectileAttackController implements AttackController {
  private double damage, minRange, maxRange;
  protected TangibleEntity controlledObject;


  public ProjectileAttackController(TangibleEntity entity) {
    if (entity == null)
      throw new IllegalArgumentException(new NullPointerException("entity"));
    this.controlledObject = entity;
    this.damage = 1.;
    this.minRange = 200.;
    this.maxRange = 600.;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getMinRange() {
    return minRange;
  }

  public void setMinRange(double minRange) {
    this.minRange = minRange;
  }

  public double getMaxRange() {
    return maxRange;
  }

  public void setMaxRange(double maxRange) {
    this.maxRange = maxRange;
  }

  @Override
  public boolean attack(TangibleEntity target) {
    double distance = controlledObject.getPosition().distance(target.getPosition());
    if (target instanceof Attackable && Math.abs(distance) < maxRange
            && Math.abs(distance) > minRange) {
      ((Attackable)target).damage(damage);
      return true;
    }
    return false;
  }
}
