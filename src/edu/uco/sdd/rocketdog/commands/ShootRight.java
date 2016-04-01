package edu.uco.sdd.rocketdog.commands;

import edu.uco.sdd.rocketdog.model.Bullet;
import javafx.scene.Group;

class ShootRight extends AbstractCommand {

    private final Group bulletGroup;
    private final double x;
    private final double y;
    

    public ShootRight(double x, double y, Group bulletGroup) {
        this.bulletGroup = bulletGroup;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        Bullet bullet = new Bullet(x,y);
        bulletGroup.getChildren().add(bullet);
    }

}
