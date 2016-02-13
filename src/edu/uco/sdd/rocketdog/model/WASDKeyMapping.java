package edu.uco.sdd.rocketdog.model;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class WASDKeyMapping implements KeyMapping {

    @Override
    public void handleKeyPressed(KeyEvent keyEvent, int speed, Level1 level) {
        if (speed != 0) {
            switch (keyEvent.getCode()) {
                case A:
                    level.rd.getSprite().setScaleX(-1);
                    level.rd.setVelX(-speed);
                    break; //rd.x -= 10;         
                case D:
                    level.rd.getSprite().setScaleX(1);
                    level.rd.setVelX(speed);
                    break; //rd.x +=  10;
                case W:
                    level.rd.setVelY(-speed);
                    break; //rd.y -= -10; 
                case S:
                    level.rd.setVelY(speed);
                    break; //rd.y +=  10; 
                case F1:
                    level.root.getChildren().remove(1);
                    level.rd = new RocketDog(new SpitzIdleAnimateStrategy(), level.rd.getPosition());
                    level.rd.addEntityClass(level.player, 1);
                    level.rd.setPosition(level.rd.getPosition());
                    level.rd.getSprite().setTranslateX(level.rd.getPosition().getX());
                    level.rd.getSprite().setTranslateY(level.rd.getPosition().getY());
                    level.root.getChildren().add(1, level.rd.getSprite());
                    break;

                case F2:
                    level.root.getChildren().remove(1);
                    level.rd = new RocketDog(new SpitzDeadAnimateStrategy(), level.rd.getPosition());
                    level.rd.addEntityClass(level.player, 1);
                    level.rd.setPosition(level.rd.getPosition());
                    level.rd.getSprite().setTranslateX(level.rd.getPosition().getX());
                    level.rd.getSprite().setTranslateY(level.rd.getPosition().getY());
                    level.root.getChildren().add(1, level.rd.getSprite());
                    break;
                case H:
                    if (level.visibleHitboxes) {
                        level.visibleHitboxes = false;
                    } else {
                        level.visibleHitboxes = true;
                    }
                    break;
            }
        } else {
            switch (keyEvent.getCode()) {
                case LEFT:
                    level.rd.getSprite().setScaleX(-1);
                    level.rd.setVelX(-speed);
                    break; //rd.x -= 10;         
                case RIGHT:
                    level.rd.getSprite().setScaleX(1);
                    level.rd.setVelX(speed);
                    break; //rd.x +=  10;
                case UP:
                    level.rd.setVelY(-speed);
                    break; //rd.y -= -10; 
                case DOWN:
                    level.rd.setVelY(speed);
                    break; //rd.y +=  10; 
            }
        }
    }

}
