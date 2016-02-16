package edu.uco.sdd.rocketdog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Level extends Scene {

    final private RocketDog rocketDog;
    final private EntityClass player;
    private ArrayList<Enemy> enemies;
    private boolean visibleHitBoxes;
    private StackPane root;
    private KeyMappingContext keyMapping;

    public Level(StackPane root, ImageView background, int width, int height) {
        super(root, width, height);
        this.root = root;

        //Initialization
        keyMapping = new KeyMappingContext();
        visibleHitBoxes = false;
        rocketDog = new RocketDog();
        player = new EntityClass("Player");
        enemies = new ArrayList();

        //Background Added to game
        root.getChildren().add(background);
        root.setAlignment(Pos.TOP_LEFT);

        //Hero information added to game
        rocketDog.setPosition(new Point2D(70, 700));
        rocketDog.addEntityClass(player, 1);
        rocketDog.getHitbox().setWidth(130);
        rocketDog.getHitbox().setHeight(130);
        root.getChildren().add(rocketDog.getSprite());
        root.getChildren().add(rocketDog.getHitbox());

        //Keyboard Handling
        this.setOnKeyPressed((KeyEvent event) -> {
            keyMapping.getKeyMapping().handleKeyPressed(this, event, 10.0d);
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            keyMapping.getKeyMapping().handleKeyReleased(this, event, 0.0d);
        });
    }

    public KeyMappingContext getKeyMapping() {
        return keyMapping;
    }

    public RocketDog getRocketDog() {
        return rocketDog;
    }

    public boolean getVisibleHitBoxes() {
        return visibleHitBoxes;
    }

    public EntityClass getPlayer() {
        return player;
    }

    public void addEnemy(Enemy enemy, double width, double height) {
        //Setup enemy hitbox information
        enemy.getHitbox().setWidth(width);
        enemy.getHitbox().setHeight(height);

        //Add enemy information to level
        enemies.add(enemy);
        root.getChildren().add(enemy.getSprite());
        root.getChildren().add(enemy.getHitbox());
    }

    public void removeEnemy(Enemy enemy) {
        //Make sure the ArrayList has the enemy within it 
        //before tyring to remove
        if (enemies.indexOf(enemy) > -1) {
            enemies.remove(enemy);
        }

        //Make sure the root has the enemy in its children
        //before ting to remove
        if (root.getChildren().indexOf(enemy.getSprite()) > -1) {
            root.getChildren().remove(enemy.getSprite());
        }

        //Make sure the root has the enemy in its children
        //before ting to remove
        if (root.getChildren().indexOf(enemy.getHitbox()) > -1) {
            root.getChildren().remove(enemy.getHitbox());
        }
    }

    public void setVisibleHitBoxes(boolean value) {
        visibleHitBoxes = value;
    }

    public void update() {

        //Update RocketDog
        rocketDog.update();

        //Set rocketDog hitbox visibility
        rocketDog.getHitbox().setVisible(visibleHitBoxes);

        Map<Entity, Boolean> changed = new HashMap<>();
        changed.put(rocketDog, true);

        enemies.stream().forEach((enemy) -> {
            changed.put(enemy, true);
            enemy.process(changed);
        });

        enemies.stream().forEach((enemy) -> {
            //Update enemy
            enemy.update();

            //Set enemy hitbox visibility
            enemy.getHitbox().setVisible(visibleHitBoxes);

            //Check for collision
            enemy.processCollision(rocketDog);
        });
    }
}
