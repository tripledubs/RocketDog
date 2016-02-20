package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.MovementController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TangibleEntity implements Entity {

    private boolean isDead;
    private Point2D position;
    private Point2D acceleration;
    private Point2D velocity;
    private Hitbox hitbox;
    private final Map<EntityClass, Integer> entityClasses = new HashMap<>();
    private ImageView sprite;

    public TangibleEntity() {
        isDead = false;
        hitbox = new Hitbox(0, 0);
        acceleration = new Point2D(0, 0);
        position = new Point2D(0, 0);
        velocity = new Point2D(0, 0);
    }

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
            if (velocity == null) {
                return false;
            } else {
                double x = velocity.getX();
                if (x >= 1 || x <= -1) {
                    position = position.add(velocity);
                    return true;
                }
                return false;
            }
        } else {
            if (velocity == null) {
                velocity = new Point2D(0, 0);
            }
            position = position.add(velocity);
            velocity = velocity.add(acceleration);
            return true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView newSprite) {
        sprite = newSprite;
    }

    public Point2D getPosition() {
        return position;
    }
    
    /** 
     * The client should not have to call new Point2D
     * every time.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPos(double x, double y) {
        setPosition(new Point2D(x,y));
    }
    
    public void setPosition(Point2D newPosition) {
        position = newPosition;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    /** 
     * The client should not have to call new Point2D
     * every time.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setVel(double x, double y) {
        setVelocity(new Point2D(x,y));
    }
    public void setVelocity(Point2D newVelocity) {
        velocity = newVelocity;
    }

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point2D newAcceleration) {
        acceleration = newAcceleration;
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

    public void processCollision(TangibleEntity otherEntity) {
        if (getHitbox().getBoundsInParent().intersects(otherEntity.getHitbox().getBoundsInParent())) {
            otherEntity.getHitbox().setStroke(Color.RED);
            getHitbox().setStroke(Color.RED);
        } else {
            getHitbox().setStroke(Color.GREEN);
            otherEntity.getHitbox().setStroke(Color.GREEN);
        }
    }

}
