package Model;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractSpitzAnimationState implements IAnimateState {

    protected Image image;
    protected int frameCount;
    protected int showIndex;
    protected Rectangle2D[] frames;
    
    public AbstractSpitzAnimationState() {
        System.out.println("ACK! Default constructor called");
        
    }
    
    public AbstractSpitzAnimationState(String imgFile, Rectangle2D... frames) {
        try {
            image = new Image(imgFile);
        } catch (IllegalArgumentException exc) {
            System.out.println(imgFile + " not found!");
            Platform.exit();
        }
        this.frames = new Rectangle2D[frames.length];
        frameCount = 0;
        showIndex = 0;

    }

    @Override
    public void handle(ImageView sprite, Rectangle2D[] frames) {
        if (frameCount % frames.length == 0) {
            showIndex++;
            if (showIndex == frames.length) {
                showIndex = 0;
            }
        }
        frameCount++;
        sprite.setViewport(frames[showIndex]);

    }

}
