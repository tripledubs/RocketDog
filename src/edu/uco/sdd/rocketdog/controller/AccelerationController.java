package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.TangibleEntity;
import java.util.Map;
import javafx.geometry.Point2D;

public class AccelerationController extends MovementController {

    private Point2D acceleration;

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point2D acceleration) {
        this.acceleration = acceleration;
    }

    public AccelerationController(TangibleEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity", new NullPointerException());
        }
        this.controlledObject = entity;
    }

    @Override
    public boolean process(Map<Entity, Boolean> changedEntities) {
        Point2D current = controlledObject.getAcceleration();
        if (acceleration != null && !acceleration.equals(current)) {
            controlledObject.setAcceleration(acceleration);
            return true;
        } else {
            return false;
        }
    }

}