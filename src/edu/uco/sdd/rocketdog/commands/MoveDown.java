package edu.uco.sdd.rocketdog.commands;

import edu.uco.sdd.rocketdog.model.TangibleEntity;
import javafx.scene.Node;

public class MoveDown extends AbstractCommand {
    private final TangibleEntity tangibleEntity;
    private final int focalSpeed;
    private final int levelHeight;

    public MoveDown(TangibleEntity tangibleEntity, int focalSpeed, int levelHeight) {
        this.tangibleEntity = tangibleEntity;
        this.focalSpeed = focalSpeed;
        this.levelHeight = levelHeight;
    }

    @Override
    public void execute() {
        // Don't go below level
        if (absoluteBounds(tangibleEntity.getSprite()).getMaxY() > levelHeight )
            return;
        Node sprite = tangibleEntity.getSprite();
        sprite.setTranslateY(sprite.getTranslateY() + focalSpeed);
    }
}
