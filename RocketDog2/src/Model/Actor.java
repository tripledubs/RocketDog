/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 /*fixed sprite
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author Sophia
 */
public abstract class Actor {
    protected List<Image> imageStates= new ArrayList<>();
    protected ImageView spriteFrame;
    protected SVGPath spriteBound;//collision Area 
    protected double ix;// X and Y position of the sprite on the display screen
    protected double iy;
    protected double px, py; //pivot point variables
    protected boolean isAlive;//bolean flags to our actor class
    protected boolean isDead;
    protected boolean isBonus;
    protected boolean isFixed;
    protected boolean isFlipV;
    protected boolean isFlipH;
    protected boolean hasValue;

    public Actor(String SVGData, double xLocation, double yLocation, Image... spriteCel) {
        spriteBound= new SVGPath();
        spriteBound.setContent(SVGData);
        spriteFrame= new ImageView(spriteCel[0]);
        imageStates.addAll(Arrays.asList(spriteCel));
        ix= xLocation;
        iy=yLocation;
        px= py = 0;
        isFixed=true;
        isAlive=isDead= isBonus=hasValue=isFlipH=isFlipV=false;
    }

    public List<Image> getImageStates() {
        return imageStates;
    }

    public void setImageStates(List<Image> imageStates) {
        this.imageStates = imageStates;
    }

    public ImageView getSpriteFrame() {
        return spriteFrame;
    }

    public void setSpriteFrame(ImageView spriteFrame) {
        this.spriteFrame = spriteFrame;
    }

    public SVGPath getSpriteBound() {
        return spriteBound;
    }

    public void setSpriteBound(SVGPath spriteBound) {
        this.spriteBound = spriteBound;
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isIsBonus() {
        return isBonus;
    }

    public void setIsBonus(boolean isBonus) {
        this.isBonus = isBonus;
    }

    public boolean isIsFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }

    public boolean isIsFlipV() {
        return isFlipV;
    }

    public void setIsFlipV(boolean isFlipV) {
        this.isFlipV = isFlipV;
    }

    public boolean isIsFlipH() {
        return isFlipH;
    }

    public void setIsFlipH(boolean isFlipH) {
        this.isFlipH = isFlipH;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }
    public abstract void update();
}
