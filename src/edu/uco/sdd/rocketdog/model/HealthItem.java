
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.BoneAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * Class for a shield power up 
 * Inherits from AidItem, see description in AidItem.java 
 * for more information.
 * 
 * @author Doobifier
 */
public class HealthItem extends AidItem { //implements IAnimateStrategy {
    private final int  healthAmount = 500;

    public HealthItem(Point2D position){
        super(position, new BoneAnimateStrategy());

    }

    @Override
    public void processCollision(TangibleEntity te){
        super.processCollision(te);

        if (isColliding() && te.getCurrentHealth() < (te.getMaximumHealth() - healthAmount)){ // Add Health
            te.setCurrentHealth(te.getCurrentHealth() + healthAmount);
            te.setState(new DamagedState());
        }
        else if (isColliding() && te.getCurrentHealth() > (te.getMaximumHealth() - healthAmount)){ //Set to Full Health
            te.setCurrentHealth(te.getMaximumHealth());
            te.setState(new FullHealthState(te.getMaximumHealth()));
        }
        if (isColliding()){
            getLevel().update(currentHealth);
            this.setDead(true);
            this.setState(new DeathState());
        }
    }
    
}
