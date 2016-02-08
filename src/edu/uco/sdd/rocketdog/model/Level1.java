/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Level1 extends Scene implements Level {

    RocketDog rd;
    boolean moved;
    BadGuy hench;
    BadGuy hench2;
    ArrayList<BadGuy> BadGuys;

    public Level1(StackPane root, int x, int y) {
        super(root, x, y);

        // Background
        Node bg = new ImageView(new Image("/Level 2.png"));
        root.getChildren().add(bg);
        root.setAlignment(Pos.TOP_LEFT); // So Origin is in top left

        // Hero
        rd = new RocketDog();
        rd.setPosition(new Point2D(70, 700));
        EntityClass player = new EntityClass("Player");
        rd.addEntityClass(player, 1);

        // Bad Guys
        BadGuys = new ArrayList();
        EntityClass enemy = new EntityClass("Enemy");
        enemy.setRelationship(player, EntityClass.Relationship.ENEMY);
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",32,32).setX(650).setY(700).setEntityClass(enemy).build());
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",64,64).setX(500).setY(700).setEntityClass(enemy).build());
        
        // Add to view
        for (BadGuy b : BadGuys) {
            root.getChildren().add(b.sprite);
        }

        // Set Controls
        this.setOnKeyPressed((KeyEvent event) -> {
            double deltax = 0., deltay = 0.;
            switch (event.getCode()) {
              case LEFT:  deltax = -10; break;
              case RIGHT: deltax =  10; break;
              case UP:    deltay = -10; break;
              case DOWN:  deltay =  10; break;
            }
            rd.setPosition(rd.getPosition().add(deltax, deltay));
        });
    }

    @Override
    public void update() {
        rd.update();
        Map<Entity, Boolean> changed = new HashMap<>();
        for (BadGuy b: BadGuys) {
            changed.put(b, true);
        }
        changed.put(rd, true);
        for (BadGuy b: BadGuys) {
            b.process(changed);
        }
        for (BadGuy b: BadGuys) {
        }
    }
}
