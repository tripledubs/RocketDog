package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.MeleeAttackController;
import edu.uco.sdd.rocketdog.controller.PatrolController;
import edu.uco.sdd.rocketdog.controller.ProjectileAttackController;
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
    private MeleeAttackController meleeAttack;
    private ProjectileAttackController projectileAttack;

    static class Builder {

        private Image img;
        private int x;
        private int y;
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

        public Enemy build() {
            Enemy newBadGuy = new Enemy(this);
            PatrolController controller = new PatrolController(newBadGuy);
            newBadGuy.addController(controller);
            controller.setRange(300.);
            controller.setStart(x - 100.);
            controller.setEnd(x + 100.);
            newBadGuy.addEntityClass(entityClass, 1);
            newBadGuy.setMeleeAttack(new MeleeAttackController(newBadGuy));
            newBadGuy.setProjectileAttack(new ProjectileAttackController(newBadGuy));
            newBadGuy.setLevel(level);
            return newBadGuy;
        }
    }

    private Enemy(Builder builder) {
        super();
        img = builder.img;
        setSprite(new ImageView(img));
        setPosition(new Point2D(builder.x, builder.y));
    }

    @Override
    public void update() {
        getSprite().setTranslateX(getPosition().getX());
        getSprite().setTranslateY(getPosition().getY());

        getHitbox().setTranslateX(getPosition().getX());
        getHitbox().setTranslateY(getPosition().getY());
        //System.out.println(getPosition().getX() + " " + getPosition().getY());
    }

    public MeleeAttackController getMeleeAttack() {
        return meleeAttack;
    }

    public void setMeleeAttack(MeleeAttackController meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public ProjectileAttackController getProjectileAttack() {
        return projectileAttack;
    }

    public void setProjectileAttack(ProjectileAttackController projectileAttack) {
        this.projectileAttack = projectileAttack;
    }

    @Override
    public void attack(TangibleEntity target) {
        if (meleeAttack != null) {
          meleeAttack.attack(target);
        }
        if (projectileAttack != null) {
          projectileAttack.attack(target);
        }
    }
}
