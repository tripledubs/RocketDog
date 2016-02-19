package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.MeleeAttackController;
import edu.uco.sdd.rocketdog.controller.PatrolController;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class Enemy extends TangibleEntity implements Attacker {

    private Image img;
    private IBehavior behavior;
    private MeleeAttackController meleeAttack;

    static class Builder {

        private Image img;
        private IBehavior behavior;
        private int x;
        private int y;
        private EntityClass entityClass;

        public Builder(String imgFile, double x, double y) {
            try {
                img = new Image(imgFile, x, y, true, false);
            } catch (IllegalArgumentException exc) {
                System.out.println("'" + imgFile + "'" + " not found, exiting...");
                Platform.exit();
            }
        }

        public Builder behavior(IBehavior b) {
            this.behavior = b;
            return this;
        }

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setEntityClass(EntityClass ec) {
            this.entityClass = ec;
            return this;
        }

        public EntityClass getEntityClass() {
            return this.entityClass;
        }

        public Enemy build() {
            Enemy newBadGuy = new Enemy(this);
            PatrolController controller = new PatrolController(newBadGuy);
            newBadGuy.addController(controller);
            controller.setRange(200.);
            controller.setStart(x - 100.);
            controller.setEnd(x + 100.);
            controller.setAttackRange(10.);
            newBadGuy.addEntityClass(entityClass, 1);
            newBadGuy.setMeleeAttack(new MeleeAttackController(newBadGuy));
            return newBadGuy;
        }
    }

    private Enemy(Builder builder) {
        super();
        img = builder.img;
        setSprite(new ImageView(img));
        behavior = builder.behavior;
        setPosition(new Point2D(builder.x, builder.y));
    }

    void update() {
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());

        getHitbox().setTranslateX(getPosition().getX());
        getHitbox().setTranslateY(getPosition().getY());
    }

    public MeleeAttackController getMeleeAttack() {
        return meleeAttack;
    }

    public void setMeleeAttack(MeleeAttackController meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    @Override
    public void attack(TangibleEntity target) {
        if (meleeAttack != null) {
          meleeAttack.attack(target);
        }
    }
}
