package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.MeleeAttackController;
import edu.uco.sdd.rocketdog.controller.StraightLineMovementController;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class Projectile extends TangibleEntity implements Attacker, Attackable {

    private Image img;
    private MeleeAttackController meleeAttack;

    @Override
    public void damage(double attackStrength) {
        this.setDead(true);
        this.getSprite().setVisible(false);
    }

    public static class Builder {

        private Image img;
        private int x;
        private int y;
        private Point2D velocity;
        private EntityClass entityClass;
        private Level level;

        public Builder(String imgFile, double x, double y) {
            try {
                img = new Image(imgFile, x, y, true, false);
            } catch (IllegalArgumentException exc) {
                System.out.println("'" + imgFile + "'" + " not found, exiting...");
                Platform.exit();
            }
        }

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setVelocity(Point2D velocity) {
          this.velocity = velocity;
          return this;
        }

        public Builder setLevel(Level level) {
          this.level = level;
          return this;
        }

        public Builder setEntityClass(EntityClass ec) {
            this.entityClass = ec;
            return this;
        }

        public EntityClass getEntityClass() {
            return this.entityClass;
        }

        public Projectile build() {
            Projectile newProjectile = new Projectile(this);
            StraightLineMovementController controller = new StraightLineMovementController(newProjectile);
            newProjectile.addController(controller);
            controller.setAcceleration(new Point2D(0, 0));
            controller.setVelocity(velocity);
            if (entityClass != null)
              newProjectile.addEntityClass(entityClass, 1);
            MeleeAttackController attackController = new MeleeAttackController(newProjectile);
            attackController.setDamage(1);
            newProjectile.setMeleeAttack(attackController);
            newProjectile.setLevel(level);
            return newProjectile;
        }
    }

    private Projectile(Builder builder) {
        super();
        img = builder.img;
        setSprite(new ImageView(img));
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
        if (!isDead() && meleeAttack != null) {
          if (meleeAttack.attack(target))
            this.setDead(true);
        }
    }
}
