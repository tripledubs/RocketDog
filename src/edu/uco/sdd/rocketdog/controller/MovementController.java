package edu.uco.sdd.rocketdog.controller;
import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.TangibleEntity;

public abstract class MovementController implements Comparable<MovementController>, Entity {
  
  private int priority;
  
  protected TangibleEntity controlledObject;

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
  
  @Override
  public int compareTo(MovementController c) {
    long result =  (long)getPriority() - (long)c.getPriority();
    if (result > Integer.MAX_VALUE)
      result = Integer.MAX_VALUE;
    else if (result < Integer.MIN_VALUE)
      result = Integer.MIN_VALUE;
    return (int)result;
  }
  
  @Override
  public boolean equals(Object o) {
    return (o instanceof MovementController) && compareTo((MovementController)o) == 0;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + this.priority;
    return hash;
  }
}
