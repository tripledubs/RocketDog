package edu.uco.sdd.rocketdog.model;

import java.util.LinkedList;
import java.util.Map;
import javafx.scene.image.ImageView;


/**
 * Represents a particular surface that will modify the movement of any
 * entities on it.
 * @author Spencer Harris
 */
public abstract class Surface extends Hitbox {

  private ImageView sprite;
  private boolean tiled;
  private LinkedList<ImageView> tiles;

  public Surface(double width, double height) {
    super(width, height);
    tiles = null;
    tiled = false;
    sprite = null;
  }

  public abstract void process(Map<Entity, Boolean> changedEntities);

  public ImageView getSprite() {
    return sprite;
  }

  public void setSprite(ImageView sprite) {
    this.sprite = sprite;
    if (sprite != null) {
      sprite.setVisible(true);
      sprite.setTranslateX(this.getTranslateX());
      sprite.setTranslateY(this.getTranslateY());
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
        sprite.setFitWidth(this.getWidth());
        sprite.setFitHeight(this.getHeight());
      }
    }
  }

  public boolean isTiled() {
    return tiled;
  }

  public void setTiled(boolean tiled) {
    this.tiled = tiled;
  }

  @Override
  public void update(double positionX, double positionY) {
    super.update(positionX, positionY);
    if (sprite != null) {
        getSprite().setTranslateX(positionX);
        getSprite().setTranslateY(positionY);
    }
  }

}
