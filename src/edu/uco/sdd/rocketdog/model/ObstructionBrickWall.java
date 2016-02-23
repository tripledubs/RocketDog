
package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * Class for a Spike Hazard 
 * Inherits from Hazard, see description in Hazard.java 
 * for more information.
 * 
 * @author Doobifier
 */
public class ObstructionBrickWall extends Obstruction { //implements IAnimateStrategy {
    
    public ObstructionBrickWall(Point2D position){
        super(position, new BrickAnimateStrategy());

    }
    
}
