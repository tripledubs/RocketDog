/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Level1 extends Scene implements Level{
    RocketDog rd;
    boolean moved;
    

    public Level1(StackPane root, int x, int y) {
        super(root, x, y);
        Node bg = new ImageView(new Image("/Level 2.png"));
        rd = new RocketDog();
        moved = false;
        rd.y = 300;
        rd.x = -300;

        root.getChildren().add(bg);
        root.getChildren().add(rd.spriteFrame);
        
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
    }
}
