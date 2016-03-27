package edu.uco.sdd.rocketdog.commands;

import edu.uco.sdd.rocketdog.model.TangibleEntity;
import javafx.scene.image.ImageView;

public class MoveLeft extends AbstractCommand {

    private final TangibleEntity tangibleEntity;
    private final int focalSpeed;

    public MoveLeft(TangibleEntity tangibleEntity, int focalSpeed) {
        this.tangibleEntity = tangibleEntity;
        this.focalSpeed = focalSpeed;
    }

    @Override
    public void execute() {
        ImageView sprite = tangibleEntity.getSprite();
        sprite.setScaleX(-1);
        sprite.setTranslateX(sprite.getTranslateX() - focalSpeed);
    }

}
