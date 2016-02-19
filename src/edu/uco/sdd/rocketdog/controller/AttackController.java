package edu.uco.sdd.rocketdog.controller;
import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.TangibleEntity;

public abstract class AttackController implements Entity {
  
  protected TangibleEntity controlledObject;
  public abstract boolean attack(TangibleEntity target);
}
