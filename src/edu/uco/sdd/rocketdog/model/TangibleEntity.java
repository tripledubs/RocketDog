package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.MovementController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class TangibleEntity implements Entity {

    private Point2D position;
    private Point2D speed;
    private Point2D acceleration;
    private final Map<EntityClass, Integer> entityClasses = new HashMap<>();
    protected ImageView sprite;

    public Map<EntityClass, Integer> getEntityClasses() {
        return new HashMap<>(entityClasses);
    }
    protected ArrayList<MovementController> controllers = new ArrayList<>();

    @Override
    public boolean process(Map<Entity, Boolean> changedEntities) {
        boolean changed = false;
        for (int i = 0; i < controllers.size(); ++i) {
            MovementController c = controllers.get(i);
            if (c.process(changedEntities)) {
                changed = true;
                if (c != controllers.get(i)) {
                    --i;
                }
            }
        }
        if (acceleration == null) {
            if (speed == null) {
                return false;
            } else {
                double x = speed.getX();
                if (x >= 1 || x <= -1) {
                    position = position.add(speed);
                    return true;
                }
                return false;
            }
        } else {
            if (speed == null) {
                speed = new Point2D(0, 0);
            }
            position = position.add(speed);
            speed = speed.add(acceleration);
            return true;
        }
    }

    public final Point2D getPosition() {
        return position;
    }

    public final void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getSpeed() {
        return speed;
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point2D acceleration) {
        this.acceleration = acceleration;
    }

    public int getEntityClassPriority(EntityClass entityClass) {
        return entityClasses.get(entityClass);
    }

    public void addEntityClass(EntityClass entityClass, int priority) {
        if (entityClass == null) {
            throw new IllegalArgumentException("entityClass", new NullPointerException());
        }
        entityClasses.put(entityClass, priority);
    }

    public void removeEntityClass(EntityClass entityClass) {
        entityClasses.remove(entityClass);
    }

    public void addController(MovementController controller) {
        controllers.remove(controller);
        controllers.add(controller);
    }

    public void removeController(MovementController controller) {
        controllers.remove(controller);
    }

}
