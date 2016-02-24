package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Animations.SpitzChargeGunAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Level;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;

public class WASDKeyMapping implements KeyMapping {

    @Override
    public void handleKeyPressed(Level currentLevel, KeyEvent keyEvent, double speed) {
        switch (keyEvent.getCode()) {
            case A:
                currentLevel.getRocketDog().getSprite().setScaleX(-1);
                currentLevel.getRocketDog().setVelocity(new Point2D(-speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case D:
                currentLevel.getRocketDog().getSprite().setScaleX(1);
                currentLevel.getRocketDog().setVelocity(new Point2D(speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case W:
                currentLevel.getRocketDog().setVelocity(new Point2D(currentLevel.getRocketDog().getVelocity().getX(), -speed));
                break;
            case S:
                currentLevel.getRocketDog().setVelocity(new Point2D(currentLevel.getRocketDog().getVelocity().getX(), speed));
                break;
            case F1:
                currentLevel.getRocketDog().setAnimation(new SpitzChargeGunAnimateStrategy());
                break;
            case F2:
                currentLevel.getRocketDog().setAnimation(new SpitzDeadAnimateStrategy());
                break;
            case H:
                currentLevel.setVisibleHitBoxes(!currentLevel.getVisibleHitBoxes());
                break;
            case O:
                //game.displayOptionsScreen();
                break;
        }
    }

    @Override
    public void handleKeyReleased(Level currentLevel, KeyEvent keyEvent, double speed) {
        switch (keyEvent.getCode()) {
            case A:
            case D:
            case W:
            case S:
                currentLevel.getRocketDog().setVelocity(new Point2D(0, 0));
                break; //rd.y +=  10; 
        }
    }
}
