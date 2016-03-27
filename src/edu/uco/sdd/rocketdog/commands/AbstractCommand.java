package edu.uco.sdd.rocketdog.commands;

import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * Methods common in more than one command go below
 * @author Dubs
 */

public abstract class AbstractCommand {
    public abstract void execute();

    protected Bounds absoluteBounds(Node x) {
        return x.localToScene(x.getBoundsInLocal());
    }
}
