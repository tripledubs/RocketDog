/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.ShieldAnimateStrategy;

/**
 * Class for a shield power up 
 * Inherits from ActiveAidItem, see description in ActiveAidItem.java 
 * for more information.
 * 
 * @author Richard Dobie
 */
public class ActiveShield extends ActiveAidItem{
    
    public ActiveShield(TangibleEntity te){
        super(te, new ShieldAnimateStrategy(), 500);
    }
}
