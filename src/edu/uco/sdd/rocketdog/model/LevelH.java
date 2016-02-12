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

public class LevelH extends Scene implements Level {

    RocketDog rd;
    boolean moved;
    BadGuy hench;
    BadGuy hench2;
    ArrayList<BadGuy> BadGuys;
    Hitbox hitbox;
    ArrayList<Hitbox> Hitboxes;
    boolean visibleHitboxes = false;

    public LevelH(StackPane root, int x, int y) {
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
        root.getChildren().add(rd.sprite);
        hitbox = new Hitbox(rd.getPosition(),100,100,rd);
        
        Hitboxes = new ArrayList();
        Hitboxes.add(hitbox);

        // Bad Guys
        BadGuys = new ArrayList();
        EntityClass enemy = new EntityClass("Enemy");
        enemy.setRelationship(player, EntityClass.Relationship.ENEMY);
        
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",32,32).setX(650).setY(700).setEntityClass(enemy).build());
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",64,64).setX(500).setY(700).setEntityClass(enemy).build());
        
        // Add to view
        for (BadGuy b : BadGuys) {
            root.getChildren().add(b.sprite);
            Hitbox bguy = new Hitbox(b.getPosition(),100,100,b);
            Hitboxes.add(bguy);
        }
        
        for (Hitbox h : Hitboxes){
            root.getChildren().add(h.getHitbox());
        }

        // Set Controls
        this.setOnKeyPressed((KeyEvent event) -> {
            double deltax = 0., deltay = 0.;
            switch (event.getCode()) {
              case LEFT:  rd.x -= 10; break;
              case RIGHT: rd.x +=  10; break;
              case UP:    rd.y -= -10; break;
              case DOWN:  rd.y +=  10; break;
              case H: if (visibleHitboxes) {
                  visibleHitboxes = false;
              } else {
                  visibleHitboxes = true;
              }
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
        for (Hitbox h: Hitboxes){
            h.update();
            h.setShown(visibleHitboxes);
            h.intersecionCheck(Hitboxes);
        }
    }
}
