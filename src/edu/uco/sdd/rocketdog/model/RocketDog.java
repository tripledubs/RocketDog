package edu.uco.sdd.rocketdog.model;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketDog extends TangibleEntity implements IAnimateStrategy{
    public ImageView sprite;
    private IAnimateStrategy animating;
    public int x,y;
    private int velX, velY;

    
    public boolean isDead;

    public RocketDog() {
        animating = new SpitzIdleAnimateStrategy();
        sprite = new ImageView(animating.getImage());
        sprite.setViewport(animating.getCurrentView());
    }
    
    RocketDog(IAnimateStrategy animating, Point2D point) {
        this.animating = animating;
        this.setPosition(point);
    }

    public void update() {
        x += velX;
        y += velY;

        this.setPosition(new Point2D(x,y));

        /** 
         * Moving the character is handled by the TangibleEntity class
         */
        sprite.setTranslateX(super.getPosition().getX());
        sprite.setTranslateY(super.getPosition().getY());
        sprite.setViewport(animating.getCurrentView());
        
        this.handle(); // Animations
    }
    
    public void setVelX(int velX){
        this.velX = velX;
    }
    
    public void setVelY(int velY){
        this.velY = velY;
    }
    
    public void changeAnimation(IAnimateStrategy newAnimation) {
        animating = newAnimation;
        sprite.setImage(animating.getImage());
    }

    @Override
    public void handle() {
        animating.handle();
    }

    @Override
    public Rectangle2D getCurrentView() {
        return animating.getCurrentView();
    }

    @Override
    public Image getImage() {
        return animating.getImage();
    }

}
