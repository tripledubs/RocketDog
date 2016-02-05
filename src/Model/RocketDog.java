package Model;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketDog {
    private Image sprite;
    public ImageView spriteFrame;
    public int x;
    public int y;
    public static final int moveSpace = 25;
    private Rectangle2D[] frames;
    int frameCount;
    int showIndex;
    
    public RocketDog() {
        try {
            sprite = new Image("Spitzer_Idle.png");
        } catch (IllegalArgumentException exc) {
            System.out.println("Dog.png not found!");
            Platform.exit();
        }
        spriteFrame = new ImageView(sprite);
        frames = new Rectangle2D[6];
        
        int miny = 31;
        int maxy = 170;
        int height = 140;
        int width=135;
        frames[0] = new Rectangle2D(41,miny,135,height);
        frames[1] = new Rectangle2D(220,miny,132,height);
        frames[2] = new Rectangle2D(399,miny,width,height);
        frames[3] = new Rectangle2D(572,miny,135,height);
        frames[4] = new Rectangle2D(748,miny,132,height);
        frames[5] = new Rectangle2D(927,miny,131,height);
    }

    public void update() {
        if (frameCount % 6 == 0) {
            showIndex++;
            if (showIndex == 6) showIndex=0;
        }
        System.out.println(showIndex);
        frameCount++;
        spriteFrame.setViewport(frames[showIndex]);
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
}
