/*
 * This class is the parent of a more specific Obstruction class
 * to add an Obstruction create a new class enheriting this class
 * see ObstructionBrick.java for example
 *
 * Level.java contains an ArrayList to hold all of the Obstructions on any
 * level. The Logic that handles Obstructions is in level.java and mostly involves
 * updating, adding and removing instances of this class.
 *
 * LevelOne.java or any other unique level is where instances and coordinates 
 * of Obstructions should be implemented.
 * 
 * NOTE: an Obstruction is an object on screen that rocketDog can not pass
 * such as a wall.
 */
package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author Richard Dobie
 */
public class Obstruction extends TangibleEntity implements IAnimateStrategy {
    private IAnimateStrategy animating; 
    
    public Obstruction(Point2D position, IAnimateStrategy animate){
        super();
        this.setPosition(position);
        animating = animate;
        setSprite(new ImageView(animating.getImage()));
        getSprite().setViewport(animating.getCurrentView());
    }
    
    /* Obstructions may or may not move so we will leave the update
     * function like the other Tangible Entities for now
     */
    public void update(){
        setPosition(new Point2D(getPosition().getX(), getPosition().getY()));
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());
        getHitbox().setTranslateX(getPosition().getX());
        getHitbox().setTranslateY(getPosition().getY());
        getSprite().setViewport(animating.getCurrentView());
        handle(); // Animations
        if (this.isColliding()){
            
        }
       
    }
    
    public void setAnimation(IAnimateStrategy newAnimation) {
        animating = newAnimation;
        getSprite().setImage(animating.getImage());
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());
    }

    @Override
    public void handle() {
        animating.handle();
    }

    @Override
    public Rectangle2D getCurrentView() {
        return animating.getCurrentView();
    }

    @Override
    public Image getImage() {
        return animating.getImage();
    }

    public void setAnimating(IAnimateStrategy animating) {
        this.animating = animating;
    }

}
