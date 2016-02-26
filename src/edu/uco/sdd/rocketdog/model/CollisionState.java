package edu.uco.sdd.rocketdog.model;

import javafx.scene.paint.Color;

public class CollisionState implements State {

    private double newHealth;

    public CollisionState(double newHealth) {
        this.newHealth = newHealth;
    }

    @Override
    public void doAction(TangibleEntity entity) {
        entity.getHitbox().setStroke(Color.RED);
        entity.setCurrentHealth(newHealth);
    }

}
