package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzIdleAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RocketDog extends TangibleEntity implements IAnimateStrategy, Attackable {

    private IAnimateStrategy animating;
    private final Text healthText;
    private int currentScore;
    public int count1 = 0, count2 = 0;

    public RocketDog() {
        super();
        currentScore = 0;
        animating = new SpitzIdleAnimateStrategy();
        setSprite(new ImageView(animating.getImage()));
        getSprite().setViewport(animating.getCurrentView());
        this.setState(new FullHealthState(20.));
        this.healthText = new Text(0, 20, Double.toString(super.getCurrentHealth()));
        this.healthText.setFont(new Font(20));
        this.healthText.setStroke(Color.GREEN);
    }

    @Override
    public void update() {
        if (isMovementRestricted())
          setPosition(new Point2D(getPosition().getX() + stuckVelocity.getX(), getPosition().getY() + stuckVelocity.getY()));
        else
          setPosition(new Point2D(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY()));

        /**
         * Moving the character is handled by the TangibleEntity class
         */
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());

        getHitbox().setTranslateX(getPosition().getX());
        getHitbox().setTranslateY(getPosition().getY());

        getSprite().setViewport(animating.getCurrentView());
        handle(); // Animations
    }

    public void setAnimation(IAnimateStrategy newAnimation) {
        animating = newAnimation;
        getSprite().setImage(animating.getImage());
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());
    }

    public void setScore(int newScore) {
        currentScore = newScore;
    }

    public int getScore() {
        return currentScore;
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

    @Override
    public String toString() {
        return new String(
                "RocketDog[x:" + this.getPosition().getX()
                + " y: " + this.getPosition().getY()
                + "]"
        );
    }

    @Override
    public void damage(double attackStrength) {
        if (this.currentHealth > 0) {
            this.currentHealth -= attackStrength;
            this.healthText.setText(Double.toString(currentHealth));
            if (this.currentHealth <= 0) {
                this.setDead(true);
                this.setAnimation(new SpitzDeadAnimateStrategy());
            }
        }
    }

    public Text getHealthText() {
        return this.healthText;
    }

}
