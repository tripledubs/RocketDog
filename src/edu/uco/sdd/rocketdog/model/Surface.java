package edu.uco.sdd.rocketdog.model;

import java.util.LinkedList;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;


/**
 * Represents a particular surface that will modify the movement of any
 * entities on it.
 * @author Spencer Harris
 */
public abstract class Surface {

  private ImageView sprite;
  private boolean tiled;
  private LinkedList<ImageView> tiles;
  private Point2D position;
  private Hitbox hitbox;

  public Surface(double width, double height) {
    hitbox = new Hitbox(width, height);
    tiles = null;
    tiled = false;
    sprite = null;
    position = Point2D.ZERO;
  }

  public abstract void process(Map<Entity, Boolean> changedEntities);

  public ImageView getSprite() {
    return sprite;
  }

  public void setSprite(ImageView sprite) {
    this.sprite = sprite;
    if (sprite != null) {
      sprite.setVisible(true);
      sprite.setTranslateX(position.getX());
      sprite.setTranslateY(position.getY());
      if (tiled) {
        // TODO: Work on this later.
        /*tiles = new LinkedList<>();
        double width = sprite.getFitWidth();
        double height = sprite.getFitHeight();
        double x = width * 2;
        double y = height * 2;
        while (x < this.getWidth()) {
          ImageView next = new ImageView(sprite.getImage());
          next.setFitWidth(width);
          next.setFitHeight(height);
          next.setTranslateX(sprite.getTranslateX() + x - width);
          next.setTranslateY(sprite.getTranslateY());
        }*/
      } else {
        tiles = null;
        sprite.setFitWidth(hitbox.getWidth());
        sprite.setFitHeight(hitbox.getHeight());
      }
    }
  }

  public boolean isTiled() {
    return tiled;
  }

  public void setTiled(boolean tiled) {
    this.tiled = tiled;
  }

  public Point2D getPosition() {
    return position;
  }

  public void setPosition(Point2D position) {
    this.position = position;
  }

  public Hitbox getHitbox() {
    return hitbox;
  }

  public void setHitbox(Hitbox hitbox) {
    this.hitbox = hitbox;
  }

  public void update() {
    hitbox.update(position.getX(), position.getY());
    if (sprite != null) {
        getSprite().setTranslateX(position.getX());
        getSprite().setTranslateY(position.getY());
    }
  }

}
