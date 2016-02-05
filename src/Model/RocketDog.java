package Model;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketDog implements IAnimateState {

    private Image image;
    private IAnimateState animating;

    public ImageView spriteFrame;
    public int x;
    public int y;
    public static final int moveSpace = 25;

    public RocketDog() {
        animating = new SpitzIdleAnimateState();

    }

    public void update() {
        animating.handle(spriteFrame,chargeFrames);
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
}
