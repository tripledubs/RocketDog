package edu.uco.sdd.rocketdog.model;

import javafx.scene.input.KeyEvent;

public interface KeyMapping {
    public void handleKeyPressed(KeyEvent keyEvent, int speed, Level1 level);
}
