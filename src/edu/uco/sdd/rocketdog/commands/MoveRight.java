package edu.uco.sdd.rocketdog.commands;

import edu.uco.sdd.rocketdog.model.TangibleEntity;
import javafx.scene.Node;

public class MoveRight extends AbstractCommand {

    private final TangibleEntity tangibleEntity;
    private final int focalSpeed;
    
    public MoveRight(TangibleEntity tangibleEntity, int focalSpeed) {
        this.tangibleEntity = tangibleEntity;
        this.focalSpeed = focalSpeed;
    }

    @Override
    public void execute() {
        tangibleEntity.setVel(+7,0);
        Node sprite = tangibleEntity.getSprite();
        sprite.setScaleX(1);
        sprite.setTranslateX(sprite.getTranslateX() + focalSpeed);
    }

}
