/*
 * This class is the parent of a more specific Hazard class
 * to add an Hazard create a new class enheriting this class
 * see HazardSpike.java for example
 *
 * Level.java contains an ArrayList to hold all of the Hazards on any
 * level. The Logic that handles Hazards is in level.java and mostly involves
 * updating, adding and removing instances of this class.
 *
 * LevelOne.java or any other unique level is where instances and coordinates 
 * of Hazards should be implemented.
 * 
 * NOTE: a Hazard is what you must place on the screen for rocketdog to 
 * attempt to avoid. after a collision occurs RocketDog should take damage 
 * or die
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author Richard Dobie
 */
public class Hazard extends TangibleEntity implements IAnimateStrategy {
    private IAnimateStrategy animating;
    protected RocketDog rd;
    
    public Hazard(Point2D position, IAnimateStrategy animate){
        super();
        this.setPosition(position);
        animating = animate;
        setSprite(new ImageView(animating.getImage()));
        getSprite().setViewport(animating.getCurrentView());
    }
    
    /* Hazards may or may not move so we will leave the update
     * function like the other Tangible Entities
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
    
    @Override
    public void processCollision(TangibleEntity te){
        super.processCollision(te);
        
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
