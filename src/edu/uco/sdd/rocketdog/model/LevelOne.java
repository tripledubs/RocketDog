package edu.uco.sdd.rocketdog.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class LevelOne extends Level {
    Text t;
    ImageView bg;
    StackPane root;
    

    public LevelOne(StackPane root, ImageView background, int width, int height) {
        super(root,background,width,height);
        this.root = root;
        
        t = new Text(10,50,"Hello");
        root.getChildren().add(t);
        bg = (ImageView) root.getChildren().get(0);

        // Bad Guys
        EntityClass enemy = new EntityClass("Enemy");
        enemy.setRelationship(getPlayer(), EntityClass.Relationship.ENEMY);
        //addEnemy(new Enemy.Builder("/Ugly Dog.png", 128, 128).setX(650).setY(600).setEntityClass(enemy).build(), 128, 128);
        //addEnemy(new Enemy.Builder("/Ugly Dog.png", 64, 64).setX(500).setY(700).setEntityClass(enemy).build(), 64, 64);
        
    }
    
    public void goLeft() {
        for (Node n: root.getChildren() ) {
            n.setTranslateX(n.getTranslateX() + 1);
            
        }
    }
    
    @Override
    public void update() {
        super.update();
        t.setText(this.getRocketDog().toString() + 
                "\n" + 
                "Background: [x: " + bg.getTranslateX()+ 
                " y: " + bg.getTranslateY() +
                "]"
        );
        
    }
}
