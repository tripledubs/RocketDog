/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Level1 extends Scene implements Level{
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
        rd.x = 70;
        rd.y = 700;

        root.getChildren().add(rd.spriteFrame);
        
        // Bad Guys
        BadGuys = new ArrayList();
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",32,32).setX(500).setY(350).build());
        BadGuys.add(new BadGuy.Builder("/Ugly Dog.png",64,64).setX(350).setY(450).build());
        
        // Add to view
        for (BadGuy b : BadGuys) {
            root.getChildren().add(b.sprite);
        }

        // Set Controls
        this.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
            case LEFT:  rd.x-=10; break;
            case RIGHT: rd.x+=10; break;
            case UP: rd.y-=10; break;
            case DOWN: rd.y+=10; break;
        }});
    }

    @Override
    public void update() {
        rd.update();
        for (BadGuy b: BadGuys) {
            b.update();
        }
    }
}
