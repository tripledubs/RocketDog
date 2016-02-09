/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.PatrolController;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class BadGuy extends TangibleEntity {
    
    private Image img;
    public ImageView sprite;
    private IBehavior behavior;

    static class Builder {
        private Image img;
        public ImageView sprite;
        private IBehavior behavior;
        public int x;
        public int y;    
        public EntityClass entityClass;
        
        public Builder(String imgFile, double x, double y) {
            try { 
                img = new Image(imgFile,x,y,true,false);
            } catch (IllegalArgumentException exc) {
                System.out.println("'" + imgFile + "'" + " not found, exiting...");
                Platform.exit();
            }
            sprite = new ImageView(img);
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
        
        public Builder setEntityClass(EntityClass ec) {
          this.entityClass = ec;
          return this;
        }
        
        public BadGuy build() {
            BadGuy newBadGuy = new BadGuy(this);
            PatrolController controller = new PatrolController(newBadGuy);
            newBadGuy.addController(controller);
            controller.setRange(200.);
            controller.setStart(x - 100.);
            controller.setEnd(x + 100.);
            newBadGuy.addEntityClass(entityClass, 1);
            return newBadGuy;
        }
    }
    
    private BadGuy(Builder b) {
        this.img = b.img;
        this.behavior = b.behavior;
        this.sprite = b.sprite;
        this.setPosition(new Point2D(b.x, b.y));
    }
    
    void update() {
        this.sprite.setTranslateX(this.getPosition().getX());
        this.sprite.setTranslateY(this.getPosition().getY());
    }
}
