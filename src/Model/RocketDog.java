package Model;

import javafx.scene.image.ImageView;

public class RocketDog implements IAnimateStrategy {

    private IAnimateStrategy animating;

    public ImageView sprite;
    public int x;
    public int y;
    public static final int moveSpace = 25;

    public RocketDog() {
        animating = new SpitzChargeGunAnimateStrategy();
        this.sprite = animating.getSprite();

    }

    public void update() {
        animating.handle();
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    @Override
    public void handle() {
        animating.handle();
    }

    @Override
    public ImageView getSprite() {
        return animating.getSprite();
    }
}
