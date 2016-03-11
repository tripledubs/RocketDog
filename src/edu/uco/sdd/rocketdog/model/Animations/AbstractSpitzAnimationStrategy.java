package edu.uco.sdd.rocketdog.model.Animations;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public abstract class AbstractSpitzAnimationStrategy implements IAnimateStrategy {

    protected Image image;
    protected int frameCount;
    protected int showIndex;
    protected Rectangle2D[] frames;
    protected Rectangle2D currentView;
    
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
        currentView = frames[0]; 
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
        currentView = frames[showIndex];
    }
    
    @Override
    public Rectangle2D getCurrentView() {
        return currentView;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
}
