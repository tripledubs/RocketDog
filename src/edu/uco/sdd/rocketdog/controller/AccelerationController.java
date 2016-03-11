package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.Level;
import edu.uco.sdd.rocketdog.model.Observer;
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

    @Override
    public Level getLevel() {
      return this.controlledObject.getLevel();
    }

    @Override
    public void setLevel(Level level) {
      throw new UnsupportedOperationException("setLevel must be called on the controlled object.");
    }

    @Override
    public void addObserver(Observer newObserver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserver(Observer oldObserver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}