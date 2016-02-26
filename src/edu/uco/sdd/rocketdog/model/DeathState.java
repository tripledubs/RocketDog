package edu.uco.sdd.rocketdog.model;

public class DeathState implements State{

    @Override
    public void doAction(TangibleEntity entity) {
        entity.setCurrentHealth(0);
    }

}
