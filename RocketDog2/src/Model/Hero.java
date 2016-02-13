/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;

/**
 *
 * @author Sophia
 * motion sprite
 */
public abstract class Hero extends Actor{
        //add data variable to hold to motion and collision, also life fuel and such
        // bounce, gravity, rotation, scaling
        protected double vX,vY;// velocity vector 
        protected double lifeSpan, fuel, damage;
        protected double offsetX, offsetY;
        protected float boundScale, boundRot, friction, gravity, bounce;
        
    public Hero(String SVGData, double xLocation, double yLocation, Image spriteCel) {
        //create spriteBound SVG path shape object.iX, IY are initial location and the image array is loaded
        super(SVGData, xLocation, yLocation, spriteCel);
        vX=vY=offsetX=offsetY=0;
        lifeSpan=1000;
        fuel=10;
        damage=0;
        boundScale= boundRot= friction= gravity= bounce=0;
        
    }
    

    @Override
    public void update() {
    
    }
    public boolean collide(Actor a){ return false;}//no collision here yet

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public double getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(double lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getBoundScale() {
        return boundScale;
    }

    public void setBoundScale(float boundScale) {
        this.boundScale = boundScale;
    }

    public double getBoundRot() {
        return boundRot;
    }

    public void setBoundRot(float boundRot) {
        this.boundRot = boundRot;
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public double getBounce() {
        return bounce;
    }

    public void setBounce(float bounce) {
        this.bounce = bounce;
    }
    
    
}
