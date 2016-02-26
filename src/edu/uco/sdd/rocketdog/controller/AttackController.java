package edu.uco.sdd.rocketdog.controller;
import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.TangibleEntity;

public interface AttackController {
  
   boolean attack(TangibleEntity target);
}
