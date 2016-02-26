package edu.uco.sdd.rocketdog.model;

import javafx.scene.paint.Color;

public class DamagedState implements State {

    @Override
    public void doAction(TangibleEntity entity) {
        entity.getHitbox().setStroke(Color.GREEN);
    }

}
