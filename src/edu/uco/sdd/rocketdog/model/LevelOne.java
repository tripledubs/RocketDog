package edu.uco.sdd.rocketdog.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LevelOne extends Level {

    public LevelOne(StackPane root, ImageView background, int width, int height) {
        super(root, background, width, height);

        // Bad Guys
        EntityClass enemy = new EntityClass("Enemy");
        enemy.setRelationship(getPlayer(), EntityClass.Relationship.ENEMY);
        addEnemy(new Enemy.Builder("/Ugly Dog.png", 128, 128).setX(650).setY(600).setEntityClass(enemy).build(), 128, 128);
        addEnemy(new Enemy.Builder("/Ugly Dog.png", 64, 64).setX(500).setY(700).setEntityClass(enemy).build(), 64, 64);

    }
}
