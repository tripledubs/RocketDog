/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
/**
 * Class for a boost power up 
 * Inherits from AidItem, see description in AidItem.java 
 * for more information.
 *
 * @author Doobifier
 */
public class BoostItem extends AidItem { //implements IAnimateStrategy {
    
    public BoostItem(Point2D position){
        super(position, new BoostAnimateStrategy());

    }
    
}
