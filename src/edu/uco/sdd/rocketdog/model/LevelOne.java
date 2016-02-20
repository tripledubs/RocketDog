package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class LevelOne extends Level {
    Text t;
    ImageView bg;
    StackPane root;
    double width;
    Group houses;
    

    public LevelOne(StackPane root, ImageView background, int width, int height) {
        super(root,background,width,height);
        this.root = root;
        this.width = width;
        
        houses = new Group();
        houses.setAutoSizeChildren(false);
        
        ImageViewLoader ldr = ImageViewLoader.getInstance();
        for (int i=0; i < 2; i++)  {
            ImageView sprite = ldr.loadImage("/Resources/houses/house1.png");
            houses.getChildren().add(sprite);
        }
        houses.setScaleX(.8);
        houses.setScaleY(.8);
        
        
        root.getChildren().add(1,houses);
        
        
        
        t = new Text(10,50,"Hello");
        root.getChildren().add(t);
        bg = (ImageView) root.getChildren().get(0);

        // Bad Guys
        EntityClass enemy = new EntityClass("Enemy");
        enemy.setRelationship(getPlayer(), EntityClass.Relationship.ENEMY);
        //addEnemy(new Enemy.Builder("/Ugly Dog.png", 128, 128).setX(650).setY(600).setEntityClass(enemy).build(), 128, 128);
        //addEnemy(new Enemy.Builder("/Ugly Dog.png", 64, 64).setX(500).setY(700).setEntityClass(enemy).build(), 64, 64);
        
        // Add random houses every 300 pixels or so
        houses.setTranslateY(280);

        
    }
    
    public void positionScreen(RocketDog rd, ImageView bg) {
        double width = RocketDogGame.GAME_SCREEN_WIDTH;
        double height = RocketDogGame.GAME_SCREEN_HEIGHT;
        
        double rdx = this.getRocketDog().getPosition().getX();
        double rdy = this.getRocketDog().getPosition().getY();

        /**
         *  Divide the screen into 10 zones
         */
        int numZones = 10;
        double[] zoneWidth = new double[numZones];
        double[] zoneHeight = new double[numZones];
        for (int i=0; i < numZones; i++) {
            zoneWidth[i] = i * (width / numZones);
            zoneHeight[i] = i * (height / numZones);
        }
        
        //RD Goes right so BG goes left
        
        if (rdx > zoneWidth[8]) {
            bg.setTranslateX(bg.getTranslateX()-5);
            houses.setTranslateX(bg.getTranslateX()-5);
            rd.setPos(zoneWidth[8],rdy);
        }
        if (rdx > zoneWidth[4]) { 
            bg.setTranslateX(bg.getTranslateX()-1);
        }
        ///
        
        //RD goes left so BG goes right
        if (rdx < zoneWidth[0]) {
            bg.setTranslateX(bg.getTranslateX()+1);
            rd.setPos(zoneWidth[0],rdy);
        }
                
        if (rdx < zoneWidth[1]) {
            bg.setTranslateX(bg.getTranslateX()+1);
        }
        if (rdy > zoneHeight[8]) {
            bg.setTranslateY(bg.getTranslateY()-5);
            rd.setPos(rdx,zoneHeight[8]);
        }
        // RD goes down so bg goes up
        if (rdy > zoneHeight[7]) {
            bg.setTranslateY(bg.getTranslateY()-1);
        }


        // RD goes up so bg goes down
        if (rdy < zoneHeight[1]) {
            bg.setTranslateY(bg.getTranslateY()+1);
        
        }
        if (rdy < zoneHeight[0]) {
            bg.setTranslateY(bg.getTranslateY()+5);
            rd.setPos(rdx,zoneHeight[0]);
         
        }
        
    }
    
    @Override
    public void update() {
        super.update();
        t.setText(this.getRocketDog().toString() + 
                "\n" + 
                "Background(translation): [x: " + bg.getTranslateX()+ 
                " y: " + bg.getTranslateY() +
                "]\n" + 
                "width" + width
        );
        positionScreen(this.getRocketDog(),bg);
        
    }
}
