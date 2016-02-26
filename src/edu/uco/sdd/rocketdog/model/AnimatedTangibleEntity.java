package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzIdleAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedTangibleEntity extends TangibleEntity implements IAnimateStrategy {

    private IAnimateStrategy animation;

    public AnimatedTangibleEntity() {
        super();
    }

    public AnimatedTangibleEntity(Point2D startPosition) {
        super(startPosition);
    }

    public AnimatedTangibleEntity(Point2D startPosition, double hitboxWidth, double hitboxHeight, int startHealth) {
        super(startPosition, hitboxWidth, hitboxHeight, startHealth);
        this.animation = new SpitzIdleAnimateStrategy();
        super.setSprite(new ImageView(animation.getImage()));
        super.getSprite().setViewport(animation.getCurrentView());
    }

    public void update() {
        //Set new Position for RocketDog
        super.setPosition(new Point2D(super.getPosition().getX() + super.getVelocity().getX(),
                super.getPosition().getY() + super.getVelocity().getY()));

        //Update Sprite location for RocketDog
        super.getSprite().setTranslateX(super.getPosition().getX());
        super.getSprite().setTranslateY(super.getPosition().getY());

        //Update Hitbox location for RocketDog
        super.getHitbox().setTranslateX(super.getPosition().getX());
        super.getHitbox().setTranslateY(super.getPosition().getY());

        //Update Animation strategy for Rocketdog
        super.getSprite().setViewport(animation.getCurrentView());
        handle(); // Animations}
    }

    @Override
    public void handle() {
        animation.handle();
    }

    @Override
    public Rectangle2D getCurrentView() {
        return animation.getCurrentView();
    }

    @Override
    public Image getImage() {
        return animation.getImage();
    }

    public void setAnimation(IAnimateStrategy newAnimation) {
        animation = newAnimation;
        super.getSprite().setImage(animation.getImage());
    }
}
