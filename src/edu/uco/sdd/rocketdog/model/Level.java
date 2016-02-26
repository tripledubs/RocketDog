package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.KeyMappingContext;
import edu.uco.sdd.rocketdog.model.Animations.SpitzDeadAnimateStrategy;
import edu.uco.sdd.rocketdog.controller.RocketDogGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Level extends Scene implements Observer, ILevel {

    final private RocketDog rocketDog;
    final private EntityClass player;
    private ArrayList<Modification> entities;
    private ArrayList<Enemy> enemies;
    private ArrayList<AidItem> AidItems;             //container of AitItems
    private ArrayList<ActiveAidItem> ActiveAidItems; //container of ActiveAidItems
    private ArrayList<Hazard> Hazards; // container of Hazards
    private ArrayList<Obstruction> Obstructions; //container of Obstructions
    private ArrayList<Projectile> projectiles;
    private boolean visibleHitBoxes;
    private Group root;
    private KeyMappingContext keyMapping;
    private boolean isDone;
    private Text scoreText;

    public Level(Group root, ImageView background, int width, int height) {
        super(root, width, height);
        this.root = root;

        //Initialization
        scoreText = new Text(5, 20, "");
        entities = new ArrayList<>();
        keyMapping = new KeyMappingContext();
        visibleHitBoxes = false;
        rocketDog = new RocketDog();
        player = new EntityClass("Player");
        enemies = new ArrayList<>();
        AidItems = new ArrayList<>();
        ActiveAidItems = new ArrayList<>();
        Hazards = new ArrayList<>();
        Obstructions = new ArrayList<>();
        projectiles = new ArrayList<>();

        //Background Added to game
        root.getChildren().add(background);
        //root.setAlignment(Pos.TOP_LEFT);

        //Hero information added to game
        rocketDog.setPosition(new Point2D(RocketDogGame.GAME_SCREEN_WIDTH / 3, RocketDogGame.GAME_SCREEN_HEIGHT / 2));
        rocketDog.addEntityClass(player, 1);
        rocketDog.getHitbox().setWidth(130);
        rocketDog.getHitbox().setHeight(130);
        rocketDog.setLevel(this);
        rocketDog.setState(new FullHealthState(1000));

        root.getChildren().add(rocketDog.getSprite());
        root.getChildren().add(rocketDog.getHitbox());
        root.getChildren().add(rocketDog.getHealthText());
        root.getChildren().add(scoreText);

        scoreText.setText("Score : " + rocketDog.getScore() + "                 Health: " + rocketDog.getCurrentHealth());
        scoreText.setFont(new Font(20));

        //Keyboard Handling
        this.setOnKeyPressed((KeyEvent event) -> {
            keyMapping.getKeyMapping().handleKeyPressed(this, event, 10.0d);
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            keyMapping.getKeyMapping().handleKeyReleased(this, event, 0.0d);
        });
    }

    public KeyMappingContext getKeyMapping() {
        return keyMapping;
    }

    public RocketDog getRocketDog() {
        return rocketDog;
    }

    public boolean getVisibleHitBoxes() {
        return visibleHitBoxes;
    }

    public EntityClass getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<TangibleEntity> getAllEntities() {
        ArrayList<TangibleEntity> entities = new ArrayList<>(1 + enemies.size());
        entities.add(getRocketDog());
        entities.addAll(getEnemies());
        return entities;
    }

    public void addEnemy(Enemy enemy, double width, double height) {
        //Setup enemy hitbox information
        enemy.getHitbox().setWidth(width);
        enemy.getHitbox().setHeight(height);
        enemy.setState(new FullHealthState(1000));

        //Add enemy information to level
        enemies.add(enemy);
        this.root.getChildren().add(enemy.getSprite());
        this.root.getChildren().add(enemy.getHitbox());
    }

    public void removeEnemy(Enemy enemy) {
        //Make sure the ArrayList has the enemy within it
        //before tyring to remove

        //Make sure the root has the enemy in its children
        //before ting to remove
        root.getChildren().remove(enemy.getSprite());

        //Make sure the root has the enemy in its children
        //before ting to remove
        root.getChildren().remove(enemy.getHitbox());
    }

    public void addAidItem(AidItem aidItem, double width, double height) {
        //Setup powerup hitbox information
        aidItem.getHitbox().setWidth(width);
        aidItem.getHitbox().setHeight(height);

        //Add powerup information to level
        AidItems.add(aidItem);
        root.getChildren().add(aidItem.getSprite());
        root.getChildren().add(aidItem.getHitbox());
    }

    public void removeAidItem(AidItem aidItem) {
        //Make sure the ArrayList has the item within it 
        //before tyring to remove

        //Make sure the root has the item in its children
        //before ting to remove
        root.getChildren().remove(aidItem.getSprite());

        //Make sure the root has the item in its children
        //before ting to remove
        root.getChildren().remove(aidItem.getHitbox());
    }

    public void addActiveAidItem(ActiveAidItem activeAidItem, double width, double height) {
        //Setup active powerup hitbox information
        activeAidItem.getHitbox().setWidth(width);
        activeAidItem.getHitbox().setHeight(height);

        //Add active powerup information to level
        ActiveAidItems.add(activeAidItem);
        root.getChildren().add(activeAidItem.getSprite());
        root.getChildren().add(activeAidItem.getHitbox());
    }

    public void removeActiveAidItem(ActiveAidItem activeAidItem) {
        //Make sure the ArrayList has the item within it 
        //before tyring to remove

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(activeAidItem.getSprite()) > -1) {
            root.getChildren().remove(activeAidItem.getSprite());
        }

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(activeAidItem.getHitbox()) > -1) {
            root.getChildren().remove(activeAidItem.getHitbox());
        }
    }

    public void addHazard(Hazard hazard, double width, double height) {
        //Setup powerup hitbox information
        hazard.getHitbox().setWidth(width);
        hazard.getHitbox().setHeight(height);

        //Add powerup information to level
        Hazards.add(hazard);
        root.getChildren().add(hazard.getSprite());
        root.getChildren().add(hazard.getHitbox());
    }

    public void removeHazard(Hazard hazard) {
        //Make sure the ArrayList has the item within it 
        //before tyring to remove
        if (Hazards.indexOf(hazard) > -1) {
            Hazards.remove(hazard);
        }

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(hazard.getSprite()) > -1) {
            root.getChildren().remove(hazard.getSprite());
        }

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(hazard.getHitbox()) > -1) {
            root.getChildren().remove(hazard.getHitbox());
        }
    }

    public void addObstruction(Obstruction obstruction, double width, double height) {
        //Setup powerup hitbox information
        obstruction.getHitbox().setWidth(width);
        obstruction.getHitbox().setHeight(height);

        //Add powerup information to level
        Obstructions.add(obstruction);
        root.getChildren().add(obstruction.getSprite());
        root.getChildren().add(obstruction.getHitbox());
    }

    public void removeHazard(Obstruction obstruction) {
        //Make sure the ArrayList has the item within it 
        //before tyring to remove
        if (Hazards.indexOf(obstruction) > -1) {
            Hazards.remove(obstruction);
        }

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(obstruction.getSprite()) > -1) {
            root.getChildren().remove(obstruction.getSprite());
        }

        //Make sure the root has the item in its children
        //before ting to remove
        if (root.getChildren().indexOf(obstruction.getHitbox()) > -1) {
            root.getChildren().remove(obstruction.getHitbox());
        }
    }

    public void addProjectile(Projectile p, double width, double height) {
        //Setup enemy hitbox information
        p.getHitbox().setWidth(width);
        p.getHitbox().setHeight(height);

        //Add enemy information to level
        projectiles.add(p);
        root.getChildren().add(p.getSprite());
        root.getChildren().add(p.getHitbox());
    }

    public void removeProjectile(Projectile p) {
        //Make sure the ArrayList has the enemy within it
        //before tyring to remove
        if (projectiles.indexOf(p) > -1) {
            projectiles.remove(p);
        }

        //Make sure the root has the enemy in its children
        //before ting to remove
        if (root.getChildren().indexOf(p.getSprite()) > -1) {
            root.getChildren().remove(p.getSprite());
        }

        //Make sure the root has the enemy in its children
        //before ting to remove
        if (root.getChildren().indexOf(p.getHitbox()) > -1) {
            root.getChildren().remove(p.getHitbox());
        }
    }

    public void addTangibleEntity(Modification newEntity) {
        entities.add(newEntity);

        //Add entity information to level
        this.root.getChildren().add(newEntity.getSprite());
        this.root.getChildren().add(newEntity.getHitbox());

    }

    public void removeTangibleEntity(Modification oldEntity) {
        //Remove entity information from root
        this.root.getChildren().remove(oldEntity.getSprite());
        this.root.getChildren().remove(oldEntity.getHitbox());
    }

    public void setVisibleHitBoxes(boolean value) {
        visibleHitBoxes = value;
    }

    @Override
    public void update() {

        //Update RocketDog
        rocketDog.update();
        rocketDog.getState().doAction(rocketDog);

        //Set rocketDog hitbox visibility
        rocketDog.getHitbox().setVisible(visibleHitBoxes);
        rocketDog.getHealthText().setVisible(visibleHitBoxes);
        rocketDog.addObserver(this);

        Map<Entity, Boolean> changed = new HashMap<>();
        changed.put(rocketDog, true);
        rocketDog.getModifications().forEach((modification) -> {
            if (!root.getChildren().contains(modification.getSprite()) && modification.isActive()) {
                rocketDog.count1 = 0;
                rocketDog.count2 = 0;
                rocketDog.getModifications().forEach((entity) -> rocketDog.count1 += (entity.getClass() == LaserAttack.class) ? 1: 0);
                rocketDog.getModifications().forEach((entity) -> rocketDog.count2 += (entity.getClass() == LargeLaserAttack.class) ? 1: 0);
                
                if(modification.getClass() == LaserAttack.class && rocketDog.count1 <= 3) {
                    addTangibleEntity(modification);
                }
                
                if(modification.getClass() == LargeLaserAttack.class && rocketDog.count2 <= 3) {
                    addTangibleEntity(modification);
                }
            }
        });

        entities.stream().forEach((entity) -> {
            //Process Entities
            changed.put(entity, true);
            entity.process(changed);
            entity.getState().doAction(entity);
            entity.update();
            System.out.println(entity.getPosition().getX());
            if(entity.getPosition().getX() <= 0 || entity.getPosition().getX() >= this.getWidth()) {
                removeTangibleEntity(entity);
                rocketDog.removeModification(entity);
                entity.setState(new DeathState());
            }
        });

        entities.stream().forEach((entity) -> {
            //Set enemy hitbox visibility
            entity.getHitbox().setVisible(rocketDog.getHitbox().isVisible());
        });

        entities.stream().forEach((entity) -> {
            //Detect Collisions
            enemies.stream().forEach((otherEntity) -> {
                if (entity.hasCollided(otherEntity)) {
                    entity.setState(new CollisionState(entity.getCurrentHealth() - otherEntity.getCurrentHealth()));
                } else if (!entity.hasCollided(otherEntity)) {
                    entity.setState(new DamagedState());
                }
            });
        });

        entities.stream().forEach((entity) -> {
            //Change entity states appropriately before detecting collision
            if (entity.getCurrentHealth() < 0) {
                entity.setState(new DeathState());
            }
        });

        entities.forEach(entity -> {
            if (entity.getState() instanceof DeathState) {
                removeTangibleEntity(entity);
            }
        });

        enemies.stream().forEach((enemy) -> {
            changed.put(enemy, true);
            enemy.process(changed);
        });

        projectiles.stream().forEach((p) -> {
            changed.put(p, true);
            p.process(changed);
        });

        enemies.stream().forEach((entity) -> {
            if (entity.hasCollided(rocketDog)) {
                entity.setState(new CollisionState(entity.getCurrentHealth() - 15));
                rocketDog.setCurrentHealth(rocketDog.getCurrentHealth() - 5);
                rocketDog.setState(new DamagedState());
                update(rocketDog.currentHealth);
            } else if (!entity.hasCollided(rocketDog)) {
                entity.setState(new DamagedState());
            }
        });

        enemies.stream().forEach((enemy) -> {
            //Update enemy
            enemy.update();

            //Set enemy hitbox visibility
            enemy.getHitbox().setVisible(visibleHitBoxes);

            //Check for collision
            enemy.processCollision(rocketDog);

            enemy.getState().doAction(enemy);
        });

        for (int i = 0; i < projectiles.size(); ++i) {
            Projectile p = projectiles.get(i);
            if (!p.isDead()) {
                //Update projectile
                p.update();

                //Set projectile hitbox visibility
                p.getHitbox().setVisible(visibleHitBoxes);

                //Check for collision
                p.processCollision(rocketDog);
            } else {
                removeProjectile(p);
                --i;
            }
        }

        AidItems.stream().forEach((aidItem) -> {
            if (aidItem != null) {
                //Update enemy
                aidItem.update();

                //Set enemy hitbox visibility
                aidItem.getHitbox().setVisible(visibleHitBoxes);

                //Check for collision
                aidItem.processCollision(rocketDog);
                if (aidItem.isColliding() && aidItem.getClass() == edu.uco.sdd.rocketdog.model.ShieldItem.class) {
                    removeAidItem(aidItem);
                    aidItem.setState(new DeathState());
                    addActiveAidItem(new ActiveShield(rocketDog), 153, 150);
                    rocketDog.setScore(rocketDog.getScore() + 5);
                    update(rocketDog.getCurrentHealth());
                } else if (aidItem.isColliding() && aidItem.getClass() == edu.uco.sdd.rocketdog.model.BoostItem.class) {
                    removeAidItem(aidItem);
                    aidItem.setState(new DeathState());
                    rocketDog.setVelocity(new Point2D(40, 0));
                    addActiveAidItem(new ActiveBoost(rocketDog), 153, 150);
                    rocketDog.setScore(rocketDog.getScore() + 5);
                    update(rocketDog.getCurrentHealth());
                }
            }
        });

        ActiveAidItems.stream().forEach((activeAidItem) -> {
            //Update active power ups
            activeAidItem.update();

            //Set active power up hitbox visibility
            activeAidItem.getHitbox().setVisible(visibleHitBoxes);

            //Check for collision
            enemies.forEach((enemy) -> activeAidItem.processCollision(enemy));

            if (!activeAidItem.isActive()) {
                removeActiveAidItem(activeAidItem);
                activeAidItem.setState(new DeathState());
            }
            if (activeAidItem.isColliding()) {
                activeAidItem.setActive(false);
            }

            if (!activeAidItem.isActive()) {
                removeActiveAidItem(activeAidItem);
                activeAidItem.setState(new DeathState());
            }
        });

        Hazards.stream().forEach((hazard) -> {
            //Update Hazard
            hazard.update();

            //Set hazard hitbox visibility
            hazard.getHitbox().setVisible(visibleHitBoxes);

            //Check for collision
            hazard.processCollision(rocketDog);
            if (hazard.isColliding()) {
                // Logic for when rocketDog collides with a hazard
                rocketDog.setAnimation(new SpitzDeadAnimateStrategy()); //needs to be handled more appropriately
            }
        });

        Obstructions.stream().forEach((obstruction) -> {
            //Update Hazard
            obstruction.update();

            //Set hazard hitbox visibility
            obstruction.getHitbox().setVisible(visibleHitBoxes);

            //Check for collision
            obstruction.processCollision(rocketDog);
            if (obstruction.isColliding()) {
                //set X and Y velocity in the opposite direction
                //then update and set velocity to 0
                //this prevents RD from moving through the obstruction
                rocketDog.setVelocity(new Point2D(-rocketDog.getVelocity().getX(), -rocketDog.getVelocity().getY()));
                rocketDog.update();
                rocketDog.setVelocity(new Point2D(0, 0));
            }
        });

        enemies.stream().forEach((entity) -> {
            //Change entity states appropriately before detecting collision
            if (entity.getCurrentHealth() < 0) {
                entity.setState(new DeathState());
                removeEnemy(entity);
                entity.getState().doAction(entity);
                rocketDog.setScore(rocketDog.getScore() + 10);
                update(rocketDog.getCurrentHealth());
            }
        });
        
        entities.removeIf(entity -> entity.getState() instanceof DeathState);
        ActiveAidItems.removeIf(entity -> entity.getState() instanceof DeathState);
        AidItems.removeIf(entity -> entity.getState() instanceof DeathState);
        enemies.removeIf(entity -> entity.getState() instanceof DeathState);
    }

    @Override
    public void update(double currentHealth) {
        //Update score
        this.scoreText.setText("Score : " + rocketDog.getScore() + "                 Health: " + rocketDog.getCurrentHealth());
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
