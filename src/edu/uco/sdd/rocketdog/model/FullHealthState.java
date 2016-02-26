package edu.uco.sdd.rocketdog.model;

public class FullHealthState implements State {

    private double newHealth;

    public FullHealthState(double newHealth) {
        this.newHealth = newHealth;
    }

    @Override
    public void doAction(TangibleEntity entity) {
        entity.setCurrentHealth(newHealth);
        entity.setMaximumHealth(newHealth);
    }
}
