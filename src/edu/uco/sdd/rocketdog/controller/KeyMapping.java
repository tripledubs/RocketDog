package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Level;
import javafx.scene.input.KeyEvent;

public interface KeyMapping {
    public void handleKeyPressed(Level currentLevel, KeyEvent keyEvent, double speed);
    public void handleKeyReleased(Level currentLevel, KeyEvent keyEvent, double speed);
}
