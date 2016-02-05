package edu.uco.sdd.rocketdog.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketDog {
    private Image sprite;
    public ImageView spriteFrame;
    public int x;
    public int y;
    public static final int moveSpace = 25;
    
    public RocketDog() {
        sprite = new Image("/Dog.png",128,128,true,true);
        spriteFrame = new ImageView(sprite);
    }

    public void update() {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
}
