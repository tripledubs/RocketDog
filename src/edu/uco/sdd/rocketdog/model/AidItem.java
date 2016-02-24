/*
 * This class is the parent of a more specific AidItem class
 * to add an Aid Item create a new class enheriting this class
 * see ShieldItem.java for example
 *
 * Level.java contains an ArrayList to hold all of the AidItems on any
 * level. The Logic that handles AidItems is in level.java and mostly involves
 * updating, adding and removing instances of this class.
 *
 * LevelOne.java or any other unique level is where instances and coordinates 
 * of AidItems should be implemented.
 * 
 * NOTE: an AidItem is what you must place on the screen for rocketdog to 
 * attempt to "pick up". after a collision occurs you should make an instance
 * of a related ActiveAidItem
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author Richard Dobie
 */
public class AidItem extends TangibleEntity implements IAnimateStrategy {
    private IAnimateStrategy animating; 
    
    public AidItem(Point2D position, IAnimateStrategy animate){
        super();
        this.setPosition(position);
        animating = animate;
        setSprite(new ImageView(animating.getImage()));
        getSprite().setViewport(animating.getCurrentView());
    }
    
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
