
package edu.uco.sdd.rocketdog.model;

/**
 * Class for a boost power up 
 * Inherits from ActiveAidItem, see description in ActiveAidItem.java 
 * for more information.
 * 
 * @author Richard Dobie
 */
 
public class ActiveBoost extends ActiveAidItem{
    
    public ActiveBoost(TangibleEntity te){
        super(te, new SpitzDashAnimateStrategy(), 6);
        //this.setOffset(218);
    }
}
