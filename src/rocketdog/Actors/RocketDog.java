/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocketdog.Actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class RocketDog {
    private Image sprite;
    public ImageView spriteFrame;
    public int x;
    public int y;
    
    public RocketDog() {
        sprite = new Image("/Ugly Dog.png",128,128,true,true);
        spriteFrame = new ImageView(sprite);
    }

    public void update() {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
    
}
