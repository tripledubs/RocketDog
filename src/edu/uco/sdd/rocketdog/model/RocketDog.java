package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketDog extends TangibleEntity {
    private Image sprite;
    public ImageView spriteFrame;
    public static final int moveSpace = 25;
    
    public RocketDog() {
        sprite = new Image("/Dog.png",128,128,true,true);
        spriteFrame = new ImageView(sprite);
        this.setPosition(new Point2D(0, 0));
    }

    public void update() {
        this.spriteFrame.setTranslateX(this.getPosition().getX());
        this.spriteFrame.setTranslateY(this.getPosition().getY());
    }
}
