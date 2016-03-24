/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.ShieldAnimateStrategy;
import javafx.scene.paint.Color;

/**
 * Class for a shield power up 
 * Inherits from ActiveAidItem, see description in ActiveAidItem.java 
 * for more information.
 * 
 * @author Richard Dobie
 */
public class ActiveShield extends ActiveAidItem {
    
    public ActiveShield(TangibleEntity te){
        super(te, new ShieldAnimateStrategy(), 500);
    }
    
    @Override
    public void processCollision(TangibleEntity te){
        super.processCollision(te);
        
        if (te.getClass() == edu.uco.sdd.rocketdog.model.Projectile.class) {
            if (this.isColliding() && this.currentHealth < 1) {
                this.setActive(false);
                te.setDead(true);
            } else if (this.isColliding()){
                this.currentHealth--;
                this.getHitbox().setStroke(Color.GREEN);
                te.setDead(true);
            } 
            
            if (this.currentHealth < 1) this.setActive(false);
        
            if (!this.isActive()) {
                this.setCurrentHealth(0);
            }
        }
        
        if (te.getClass() == edu.uco.sdd.rocketdog.model.Enemy.class) {
            if (this.isColliding() && this.currentHealth < 1) {
                this.setActive(true);
            } else if (this.isColliding()){
                this.currentHealth--;
                this.getHitbox().setStroke(Color.GREEN);
            } 
            
            if (this.currentHealth < 1) this.setActive(false);
        
            if (!this.isActive()) {
                this.setCurrentHealth(0);
            }
        }
                
    }

    
    /*public void damage(double attackStrength){
        if (this.currentHealth > 0) {
            this.currentHealth -= attackStrength;
            if (this.currentHealth <= 0) {
                this.setDead(true);
                this.setActive(false);
            }
        }
    }*/
}
