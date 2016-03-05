package edu.uco.sdd.rocketdog.model;

import java.util.Map;

/**
 * Represents a slick surface.
 * @author Spencer Harris
 */
public class Ice extends Surface {

  public Ice(double width, double height) {
    super(width, height);
  }

  @Override
  public void process(Map<Entity, Boolean> changedEntities) {
    changedEntities.forEach((Entity e, Boolean p) -> {
      if (p && e instanceof TangibleEntity) {
        TangibleEntity te = (TangibleEntity) e;
        if (te.getHitbox().getBoundsInParent().intersects(this.getHitbox().getBoundsInParent())
                && te.getVelocity().magnitude() > 10) {
          te.setMovementRestricted(true);
        } else {
          te.setMovementRestricted(false);
        }
      }
    });
  }

}
