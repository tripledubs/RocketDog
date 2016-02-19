package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.EntityClass;
import edu.uco.sdd.rocketdog.model.TangibleEntity;
import java.util.Map;
import javafx.geometry.Point2D;

public class PatrolController extends AccelerationController {
  private double range, start, end, attackRange;
  private TangibleEntity target = null;

  public double getRange() {
    return range;
  }

  public void setRange(double range) {
    this.range = range;
  }

  public double getAttackRange() {
    return attackRange;
  }

  public void setAttackRange(double range) {
    this.attackRange = range;
  }

  public double getStart() {
    return start;
  }

  public void setStart(double start) {
    this.start = start;
  }

  public double getEnd() {
    return end;
  }

  public void setEnd(double end) {
    this.end = end;
  }
  
  
  public PatrolController(TangibleEntity entity) {
    super(entity);
  }

  @Override
  public boolean process(Map<Entity, Boolean> changedEntities) {
    Point2D newAccel = new Point2D(0, 0);
    this.setAcceleration(newAccel);
    final Boolean thisChanged = changedEntities.get(controlledObject);
    if (thisChanged != null && !thisChanged)  // removed
      return false;
    target = null;
    changedEntities.forEach((Entity e, Boolean p) -> {
      if (p && e != controlledObject && e instanceof TangibleEntity) {
        TangibleEntity te = (TangibleEntity) e;
        Point2D pos = controlledObject.getPosition();
        Point2D otherPos = te.getPosition();
        if (pos != null && otherPos != null && Math.abs(pos.distance(otherPos)) <= range) {
          Map<EntityClass, Integer> classes = controlledObject.getEntityClasses();
          EntityClass.Relationship rel = null;
          int maxPriority = 0;
          for (Map.Entry<EntityClass, Integer> myEntry : classes.entrySet()) {
            if (myEntry.getValue() > maxPriority) {
              for (EntityClass otherClass : te.getEntityClasses().keySet()) {
                EntityClass.Relationship otherRel = myEntry.getKey().getRelationship(otherClass);
                if (otherRel != null) {
                  maxPriority = myEntry.getValue();
                  rel = otherRel;
                }
              }
            }
          }
          if (rel == EntityClass.Relationship.ENEMY) {
            /*if (pos.getX() < otherPos.getX()) {
              controlledObject.setSpeed(new Point2D(10, 0));
            } else {
              controlledObject.setSpeed(new Point2D(-10, 0));
            }
            end = otherPos.getX();*/
            target = te;
          }
        }
      }
    });
    double currentX = controlledObject.getPosition().getX();
    if (target == null) {
      if (start < end) {
        if (currentX > end) {
          double temp = end;
          end = start;
          start = temp;
        }
        controlledObject.setVelocity(new Point2D(2.5, 0));
      } else {
        if (currentX < end) {
          double temp = end;
          end = start;
          start = temp;
        }
        controlledObject.setVelocity(new Point2D(-2.5, 0));
      }
    } else {
      double targetX = target.getPosition().getX();
      if (targetX - currentX > 10) {  // we are left of target
        controlledObject.setVelocity(new Point2D(7.5, 0));
      } else if (currentX - targetX > 10) {  // we are right of target
        controlledObject.setVelocity(new Point2D(-7.5, 0));
      } else {
        controlledObject.setVelocity(new Point2D(0, 0));
      }
      if (Math.abs(controlledObject.getPosition().distance(target.getPosition())) < attackRange) {
        //TODO: implement Attacker interface
      }
    }
    if (controlledObject.getVelocity() == null || Math.abs(controlledObject.getVelocity().getX()) < 1)
      controlledObject.setVelocity(new Point2D(2.5, 0));
    return true;
  }
}