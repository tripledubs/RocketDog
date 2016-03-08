
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.SpikeAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * Class for a Spike Hazard 
 * Inherits from Hazard, see description in Hazard.java 
 * for more information.
 * 
 * @author Doobifier
 */
public class HazardSpikes extends Hazard { //implements IAnimateStrategy {
    private int damage;
    
    public HazardSpikes(Point2D position){
        super(position, new SpikeAnimateStrategy());

    }
    
    @Override
    public void processCollision(TangibleEntity te){
        super.processCollision(te);
        
        if (this.isColliding()) {
                //set X and Y velocity in the opposite direction
                //then update and set velocity to 0
                //this prevents RD from moving through the obstruction
                te.setVelocity(new Point2D(-te.getVelocity().getX(), -te.getVelocity().getY()));
                te.update();
                te.setVelocity(new Point2D(0, 0));
                te.setState(new DamagedState());
            }
    }
    
}
