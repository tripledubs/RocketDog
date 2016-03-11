/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Modification extends AnimatedTangibleEntity {

    private boolean activation;

    public Modification(IAnimateStrategy animation) {
        this(new Point2D(0, 0), 0, 0, animation);
    }

    public Modification(double hitboxWidth, double hitboxHeight, IAnimateStrategy animation) {
        this(new Point2D(0, 0), hitboxWidth, hitboxHeight, animation);
    }

    public Modification(Point2D position, double hitboxWidth, double hitboxHeight, IAnimateStrategy animation) {
        super(position, hitboxWidth, hitboxHeight, 0);
        super.setAnimation(animation);
        super.setSprite(new ImageView(animation.getImage()));
        super.getSprite().setViewport(animation.getCurrentView());
        this.activation = false;
    }

    public void setActive(boolean newActiveState) {
        activation = newActiveState;
    }

    public boolean isActive() {
        return this.activation;
    }
}
