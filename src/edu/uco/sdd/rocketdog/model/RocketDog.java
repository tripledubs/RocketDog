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

public class RocketDog extends TangibleEntity implements IAnimateStrategy, Attackable{

    private IAnimateStrategy animating;
    private double health;
    private final Text healthText;

    public RocketDog() {
        super();
        animating = new SpitzIdleAnimateStrategy();
        setSprite(new ImageView(animating.getImage()));
        getSprite().setViewport(animating.getCurrentView());
        this.health = 20.;
        this.healthText = new Text(0, 20, Double.toString(health));
        this.healthText.setFont(new Font(20));
        this.healthText.setStroke(Color.GREEN);
    }

    public void update() {
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
                "RocketDog[x:" + this.getPosition().getX() + 
                " y: " + this.getPosition().getY() + 
                "]"
        );
    }

    @Override
    public void damage(double attackStrength) {
      if (this.health > 0) {
        this.health -= attackStrength;
        this.healthText.setText(Double.toString(health));
        if (this.health <= 0) {
          this.setDead(true);
          this.setAnimation(new SpitzDeadAnimateStrategy());
        }
      }
    }

    public Text getHealthText() {
      return this.healthText;
    }

}
