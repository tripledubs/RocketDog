package Model;

import javafx.scene.image.ImageView;

public class RocketDog implements IAnimateStrategy {

    private IAnimateStrategy animating;

    public ImageView sprite;
    public int x;
    public int y;
    public static final int moveSpace = 25;
    
    public boolean isDead;

    public RocketDog() {
        animating = new SpitzIdleAnimateStrategy();
        this.sprite = animating.getSprite();
    }
    
    RocketDog(IAnimateStrategy animating) {
        this.animating = animating;
        this.sprite = animating.getSprite();
    }
    
    RocketDog(IAnimateStrategy animating, int x, int y) {
        this.animating = animating;
        this.sprite = animating.getSprite();
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (isDead) {
            y+=5;
        }
        animating.handle();
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }
    
    public void changeAnimation(IAnimateStrategy newAnimation) {
        this.animating = newAnimation;
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
