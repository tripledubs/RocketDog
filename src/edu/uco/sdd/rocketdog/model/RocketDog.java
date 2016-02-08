package edu.uco.sdd.rocketdog.model;
import javafx.scene.image.ImageView;

public class RocketDog extends TangibleEntity implements IAnimateStrategy{
    public ImageView sprite;
    private IAnimateStrategy animating;
    public int x,y;

    
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
