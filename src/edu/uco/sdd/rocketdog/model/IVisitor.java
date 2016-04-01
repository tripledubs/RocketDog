package edu.uco.sdd.rocketdog.model;

/**
 * Every class that implements this interface must implement
 * methods to collide with every type.
 * @author Dubs
 */

public interface IVisitor {
    void visit (RocketDog rd);
    void visit (Enemy e);
    void visit (TangibleEntity te);
    void visit (Obstruction obstruction);
    void visit (AidItem ai);
    void visit (UglyDog ud);
    void visit (Bullet b);
}
