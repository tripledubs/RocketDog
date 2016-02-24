
package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * Class for a shield power up 
 * Inherits from AidItem, see description in AidItem.java 
 * for more information.
 * 
 * @author Doobifier
 */
public class ShieldItem extends AidItem { //implements IAnimateStrategy {
    
    public ShieldItem(Point2D position){
        super(position, new PowerUpAnimateStrategy());

    }
    
}
