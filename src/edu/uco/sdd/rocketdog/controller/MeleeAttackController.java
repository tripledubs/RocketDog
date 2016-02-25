package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Attackable;
import edu.uco.sdd.rocketdog.model.TangibleEntity;

/**
 * Attack controller for melee entities.
 * @author Spencer Harris
 */
public class MeleeAttackController implements AttackController {
  private double damage;
  protected TangibleEntity controlledObject;


  public MeleeAttackController(TangibleEntity entity) {
    if (entity == null)
      throw new IllegalArgumentException(new NullPointerException("entity"));
    this.controlledObject = entity;
    this.damage = 2.5;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  @Override
  public boolean attack(TangibleEntity target) {
    if (target instanceof Attackable) {
      ((Attackable)target).damage(damage);
      return true;
    }
    return false;
  }
}
