package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Animations.SpitzChargeGunAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import edu.uco.sdd.rocketdog.model.LargeLaserAttack;
import edu.uco.sdd.rocketdog.model.LaserAttack;
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
            case J:
                //If RocketDog is facing left, shoot left, otherwise shoot right
                if (currentLevel.getRocketDog().getSprite().getScaleX() == -1) {
                    currentLevel.getRocketDog().addModification(new LaserAttack(currentLevel.getRocketDog().getPosition().add(0, 65), new Point2D(-5.0d, 0), true));
                } else {
                    currentLevel.getRocketDog().addModification(new LaserAttack(currentLevel.getRocketDog().getPosition().add(100, 65), new Point2D(5.0d, 0), true));
                }
                break;
            case K:
                //If RocketDog is facing left, shoot left, otherwise shoot right
                if (currentLevel.getRocketDog().getSprite().getScaleX() == -1) {
                    currentLevel.getRocketDog().addModification(new LargeLaserAttack(currentLevel.getRocketDog().getPosition().add(0, 15), new Point2D(-5.0d, 0), true));
                } else {
                    currentLevel.getRocketDog().addModification(new LargeLaserAttack(currentLevel.getRocketDog().getPosition().add(100, 15), new Point2D(5.0d, 0), true));
                }
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
