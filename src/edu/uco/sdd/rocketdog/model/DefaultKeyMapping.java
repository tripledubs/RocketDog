package edu.uco.sdd.rocketdog.model;

import javafx.scene.input.KeyEvent;

public class DefaultKeyMapping implements KeyMapping{

    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getCode()){    
                    case UP: 
			//Move Up
			break;
                    case DOWN:
			//Move DOWN
			break;
                    case LEFT:
			//Move LEFT
			break;
                    case RIGHT:
			//Move Right
			break;
        }
    }

    
}
