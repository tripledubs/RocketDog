package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Point2D;
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
        
        // Aid Items
        addAidItem(new ShieldItem(new Point2D(200,200)),56,56);
        addAidItem(new ShieldItem(new Point2D(800,200)),56,56);
        //addAidItem(new BoostItem(new Point2D(400,200)),56,56);
        
        // Hazards
        addHazard(new HazardSpikes(new Point2D(936,300)),64,64);
        addHazard(new HazardSpikes(new Point2D(936,236)),64,64);
        addHazard(new HazardSpikes(new Point2D(936,172)),64,64);
        
        //Obstructions
        addObstruction(new ObstructionBrickWall(new Point2D(700,300)),64,64);
        addObstruction(new ObstructionBrickWall(new Point2D(700,236)),64,64);
        addObstruction(new ObstructionBrickWall(new Point2D(700,172)),64,64);
        addObstruction(new ObstructionBrickWall(new Point2D(700,108)),64,64);
        addObstruction(new ObstructionBrickWall(new Point2D(700,44)),64,64);
        addObstruction(new ObstructionBrickWall(new Point2D(700,-20)),64,64);
    }
}
