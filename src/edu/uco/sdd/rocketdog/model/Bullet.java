/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.model.Animations.IAnimateStrategy;
import edu.uco.sdd.rocketdog.model.Animations.SpitzLaserWeaponAnimateStrategy;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dubs
 */
public final class Bullet extends ImageView implements IVisitor, IElement, IAnimateStrategy{

    private IAnimateStrategy animating;
    private Point2D acceleration;

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point2D acceleration) {
        this.acceleration = acceleration;
    }

    public Bullet(double x, double y) {
        super(new Image("/Shoot.png"));
        animating = new SpitzLaserWeaponAnimateStrategy();
        setViewport(animating.getCurrentView());
        setTranslateX(x); // Initial Location
        setTranslateY(y); // Initial Location
    }

    public Bullet(double x, double y, Point2D acceleration) {
        this(x,y);
        this.setAcceleration(acceleration);
    }

    @Override
    public void handle() {
        animating.handle();
    }

    @Override
    public void visit(RocketDog rd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Enemy e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(TangibleEntity te) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Obstruction obstruction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AidItem ai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(UglyDog ud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(IVisitor v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getCurrentView() {
        return animating.getCurrentView();
    }

    public void update() {
        this.setViewport(animating.getCurrentView());
        handle();

    }

    @Override
    public void visit(Bullet b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
