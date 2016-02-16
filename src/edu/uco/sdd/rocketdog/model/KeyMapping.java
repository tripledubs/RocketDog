package edu.uco.sdd.rocketdog.model;

import javafx.scene.input.KeyEvent;

public interface KeyMapping {
    public void handleKeyPressed(Level currentLevel, KeyEvent keyEvent, double speed);
    public void handleKeyReleased(Level currentLevel, KeyEvent keyEvent, double speed);
}
