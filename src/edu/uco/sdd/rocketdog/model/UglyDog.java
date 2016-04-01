package edu.uco.sdd.rocketdog.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UglyDog extends ImageView implements IVisitor, IElement {

    public UglyDog(String s) {
        super(new Image(s,48,48,false,true));
    }

    public void update() {
        setTranslateX(this.getTranslateX()+1);
    }

    @Override
    public void visit(RocketDog rd) {
        System.out.println("Collision with rocketdog");
    }

    @Override
    public void visit(Enemy e) {
    }

    @Override
    public void visit(TangibleEntity te) {
    }

    @Override
    public void visit(Obstruction obstruction) {
    }

    @Override
    public void visit(AidItem ai) {
    }

    @Override
    public void accept(IVisitor v) {
    }

    @Override
    public void visit(UglyDog ud) {
    }

    @Override
    public void visit(Bullet b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
