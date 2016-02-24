/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.PatrolController;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public class Enemy extends TangibleEntity {

    private Image img;

    static class Builder {

        private Image img;
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
            newBadGuy.addEntityClass(entityClass, 1);
            return newBadGuy;
        }
    }

    private Enemy(Builder builder) {
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
}
