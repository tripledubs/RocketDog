/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class BadGuy {
    
    private Image img;
    public ImageView sprite;
    private IBehavior behavior;
    public int x;
    public int y;

    static class Builder {
        private Image img;
        public ImageView sprite;
        private IBehavior behavior;
        public int x;
        public int y;    
        
        public Builder(String imgFile, double x, double y) {
            this.img = new Image(imgFile,x,y,true,false);
            sprite = new ImageView(this.img);
        }
        
        public Builder behavior(IBehavior b) {
            this.behavior = b;
            return this;
        }
        
        public Builder setX(int x) {
            this.x = x;
            return this;
        }
        
        public Builder setY(int y) {
            this.y = y;
            return this;
        }
        
        public BadGuy build() {
            return new BadGuy(this);
        }
    }
    
    private BadGuy(Builder b) {
        this.img = b.img;
        this.behavior = b.behavior;
        this.sprite = b.sprite;
        this.x = b.x;
        this.y = b.y;
    }
    
    void update() {
        System.out.println("X:" + this.x + "Y:" + this.y);
        x-=5;
        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
    }
}
