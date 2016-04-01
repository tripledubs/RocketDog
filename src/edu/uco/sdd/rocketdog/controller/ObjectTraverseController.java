package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.Entity;
import edu.uco.sdd.rocketdog.model.Obstruction;
import edu.uco.sdd.rocketdog.model.TangibleEntity;
import java.util.Map;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class ObjectTraverseController extends AccelerationController {
    
    private Obstruction target;
    private MovementController next;
    private Point2D nextVelocity, nextAcceleration, destPoint, xVel, yVel, currVel;

    public ObjectTraverseController(TangibleEntity entity, Obstruction target, MovementController nextController, Point2D nextVelocity, Point2D nextAcceleration) {
        super(entity);
        this.target = target;
        this.next = nextController;
        this.nextVelocity = nextVelocity;
        this.nextAcceleration = nextAcceleration;
        findPathOut();
    }

    public Obstruction getTarget() {
        return target;
    }

    public void setTarget(Obstruction target) {
        this.target = target;
    }

    public MovementController getNext() {
        return next;
    }

    public void setNext(MovementController next) {
        this.next = next;
    }

    public Point2D getNextVelocity() {
        return nextVelocity;
    }

    public void setNextVelocity(Point2D nextVelocity) {
        this.nextVelocity = nextVelocity;
    }

    public Point2D getNextAcceleration() {
        return nextAcceleration;
    }

    public void setNextAcceleration(Point2D nextAcceleration) {
        this.nextAcceleration = nextAcceleration;
    }

    private void findPathOut() {
        Bounds cbtemp = controlledObject.getHitbox().getBoundsInParent(),
                targetBounds = target.getHitbox().getBoundsInParent(),
                controlledBounds;
        double x = cbtemp.getMinX(), y = cbtemp.getMinY(), width = cbtemp.getWidth(), height = cbtemp.getHeight(), vel = nextVelocity.magnitude();
        Point2D velAdd = new Point2D(nextVelocity.getX(), nextVelocity.getY());
        do {
            x += velAdd.getX();
            y += velAdd.getY();
            controlledBounds = new BoundingBox(x, y, width, height);
            velAdd = velAdd.add(nextAcceleration);
        } while (controlledBounds.intersects(target.getHitbox().getBoundsInParent()));
        destPoint = new Point2D(x, y);
        boolean blockedHorizontal = true, blockedVertical = true;
        if (cbtemp.getMinX() + nextVelocity.getX() > targetBounds.getMaxX() || cbtemp.getMaxX() + nextVelocity.getX() < targetBounds.getMinX()) {
            blockedVertical = false;
        }
        if (cbtemp.getMinY() + nextVelocity.getY() > targetBounds.getMaxY() || cbtemp.getMaxY() + nextVelocity.getY() < targetBounds.getMinY()) {
            blockedHorizontal = false;
        }
        double xLeft = cbtemp.getMaxX() - targetBounds.getMinX();
        double xRight = targetBounds.getMaxX() - cbtemp.getMinX();
        double yUp = cbtemp.getMaxY() - targetBounds.getMaxY();
        double yDown = targetBounds.getMaxY() - cbtemp.getMinY();
    }

    @Override
    public boolean process(Map<Entity, Boolean> changedEntities) {
        Bounds cbtemp = controlledObject.getHitbox().getBoundsInParent();
        Bounds controlledBounds = new BoundingBox(cbtemp.getMinX() + nextVelocity.getX(), cbtemp.getMinY() + nextVelocity.getY(), cbtemp.getWidth(), cbtemp.getHeight());

        if (controlledBounds.intersects(target.getHitbox().getBoundsInParent())) {
            controlledObject.setVelocity(new Point2D(0, 0));
        }
        return true;
    }

}
