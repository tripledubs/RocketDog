package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Animations.SpitzChargeGunAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import edu.uco.sdd.rocketdog.model.FullHealthState;
import edu.uco.sdd.rocketdog.model.LargeLaserAttack;
import edu.uco.sdd.rocketdog.model.LaserAttack;
import edu.uco.sdd.rocketdog.model.Level;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class DefaultKeyMapping implements KeyMapping {

    @Override
    public void handleKeyPressed(Level currentLevel, KeyEvent keyEvent, double speed) {
        switch (keyEvent.getCode()) {
            case LEFT:
                currentLevel.getRocketDog().getSprite().setScaleX(-1); // Flip image so facing left
                currentLevel.getRocketDog().setVelocity(new Point2D(-speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case RIGHT:
                currentLevel.getRocketDog().getSprite().setScaleX(1); // Flip image so facing right
                currentLevel.getRocketDog().setVelocity(new Point2D(speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case UP:
                currentLevel.getRocketDog().setVelocity(new Point2D(currentLevel.getRocketDog().getVelocity().getX(), -speed));
                break;
            case DOWN:
                currentLevel.getRocketDog().setVelocity(new Point2D(currentLevel.getRocketDog().getVelocity().getX(), speed));
                break;
            case J:
                int i = currentLevel.checkFiredLaser();
                if (i == -1) {
                    break;
                }
                currentLevel.getLaserWeapon(i).getHitbox().setStroke(Color.GREEN);
                currentLevel.getLaserWeapon(i).setVisableOn();
                currentLevel.getLaserWeapon(i).setPosition(new Point2D(currentLevel.getRocketDog().getPosition().getX() + 100,
                        currentLevel.getRocketDog().getPosition().getY() + 65));
                currentLevel.getLaserWeapon(i).setVelocity(new Point2D(speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case K:
                int j = currentLevel.checkFiredLargerLaser();
                if (j == -1) {
                    break;
                }
                currentLevel.getLargeLaserWeapon(j).getHitbox().setStroke(Color.GREEN);
                currentLevel.getLargeLaserWeapon(j).setVisableOn();
                currentLevel.getLargeLaserWeapon(j).setPosition(new Point2D(currentLevel.getRocketDog().getPosition().getX() + 100,
                        currentLevel.getRocketDog().getPosition().getY() + 15));
                currentLevel.getLargeLaserWeapon(j).setVelocity(new Point2D(speed, currentLevel.getRocketDog().getVelocity().getY()));
                break;
            case F1:
                currentLevel.getRocketDog().setState(new FullHealthState(1000));
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
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                currentLevel.getRocketDog().setVelocity(new Point2D(0, 0));
                break; //rd.y +=  10; 
        }
    }
}
