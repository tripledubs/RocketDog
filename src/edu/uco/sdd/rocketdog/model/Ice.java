package edu.uco.sdd.rocketdog.model;

import java.util.Map;
import javafx.scene.image.ImageView;

/**
 * Represents a slick surface.
 * @author Spencer Harris
 */
public class Ice extends Surface {

  public Ice(double width, double height) {
    super(width, height);
    ImageView sprite = new ImageView("/chiseled_ice.jpg");
    this.setSprite(sprite);
    sprite.setFitHeight(height);
    sprite.setFitWidth(width);
  }

  @Override
  public void process(Map<Entity, Boolean> changedEntities) {
    changedEntities.forEach((Entity e, Boolean p) -> {
      if (p && e instanceof TangibleEntity) {
        TangibleEntity te = (TangibleEntity) e;
        Hitbox teh = te.getHitbox();
        Hitbox meh = this.getHitbox();
        double mag = te.getActualVelocity().magnitude();
        if (teh.getBoundsInParent().intersects(meh.getBoundsInParent())
                && mag > 2) {
          te.setMovementRestricted(true);
        } else {
          te.setMovementRestricted(false);
        }
      }
    });
  }

}
