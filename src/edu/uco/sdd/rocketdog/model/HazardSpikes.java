
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
    private final int damage = 500;
    private final int MAX_COUNTER = 50;
    private boolean disabled;     //prevetns RD from taking damage every update that he toouches the spike
    private int disabledCounter;  //timer for how long spike is disabled

    
    public HazardSpikes(Point2D position){
        super(position, new SpikeAnimateStrategy());

    }

    @Override
    public void update(){
        super.update();

        if (disabled && disabledCounter >= 0){
            disabledCounter--;
        } else if (disabledCounter < 1){
            disabled = false;
            disabledCounter = MAX_COUNTER;
        }
    }
    
    @Override
    public void processCollision(TangibleEntity te){
        super.processCollision(te);
        
        if (this.isColliding()) {
                //set X and Y velocity in the opposite direction
                //then update and set velocity to 0
                //this prevents RD from moving through the obstruction
                this.rd = (RocketDog)te;
                this.rd.setHorzSpeed(0);
                this.rd.setVertSpeed(0);
                te.setVelocity(new Point2D(-te.getVelocity().getX(), -te.getVelocity().getY()));
                te.update();
                te.setVelocity(new Point2D(0, 0));
                if (!disabled){
                    rd.setState(new DamagedState());
                    rd.setState(new CollisionState(rd.getCurrentHealth() - damage));
                    disabled = true;
                    disabledCounter = MAX_COUNTER;
                }

            }
    }
    
}
