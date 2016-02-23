/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is an instance for every hitbox in the game, it can be assigned to
 * other objects and initialized for what type of outcome is expected depending
 * on the type of objects colliding
 *
 * @author Doobifier
 */
public class Hitbox extends Rectangle {
    
    public Hitbox(double width, double height) {
        super(width, height);
        setFill(Color.TRANSPARENT);
        setStroke(Color.GREEN);
        setStrokeWidth(3);
    }

    public void update(double positionX, double positionY) {
        setTranslateX(positionX);
        setTranslateY(positionY);
    }
}
