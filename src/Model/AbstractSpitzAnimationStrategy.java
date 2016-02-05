package Model;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    protected Image image;
    public ImageView sprite;
    protected int frameCount;
    protected int showIndex;
    protected Rectangle2D[] frames;
    
    public AbstractSpitzAnimationStrategy() {
        System.out.println("ACK! Default constructor called");
        
    }
    
    public AbstractSpitzAnimationStrategy(String imgFile, Rectangle2D... frames) {
        try {
            image = new Image(imgFile);
        } catch (IllegalArgumentException exc) {
            System.out.println(imgFile + " not found!");
            Platform.exit();
        }
        this.frames = frames;
        frameCount = 0;
        showIndex = 0;
        sprite = new ImageView(image);
        sprite.setViewport(frames[0]);
    }

    @Override
    public void handle() {
        if (frameCount % frames.length == 0) {
            showIndex++;
            if (showIndex == frames.length) {
                showIndex = 0;
            }
        }
        frameCount++;
        sprite.setViewport(frames[showIndex]);

    }
    
    @Override
    public ImageView getSprite() {
        return this.sprite;
    }

}
