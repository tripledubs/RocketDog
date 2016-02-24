package edu.uco.sdd.rocketdog.model;

import Exceptions.LevelNotFound;
import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_HEIGHT;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_WIDTH;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LevelFactory {

    protected String level;
    private Group root;
    ImageViewLoader ldr;

    public LevelFactory(String level) {
        this.level = level;
        ldr = ImageViewLoader.getInstance();
    }

    public Parent getRoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Parent getRoot(String level) {
        switch (level) {
            case "Splash":
                return new StackPane();
            case "One":
                return new Group();
        }
        throw new LevelNotFound(
                level + " is not valid for getRoot in Level Factory"
        );
    }

    public Scene getLevel() {
        switch (level) {
            case "Splash":
                return new SplashLevel((StackPane) getRoot(level));
            case "One":
                return new LevelOne(new Group(), ldr.loadImage("/Level 2.png"), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
        }
        throw new LevelNotFound(level + " is not valid for getRoot in Level Factory");
    }
}
