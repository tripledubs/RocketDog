package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.exceptions.LevelNotFound;
import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_HEIGHT;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_WIDTH;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SceneFactory {

    protected String level;
    ImageViewLoader ldr;

    public SceneFactory(String level) {
        this.level = level;
        ldr = ImageViewLoader.getInstance();
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public Scene getLevel() {
        switch (level) {
            case "Splash":
                return new SplashLevel(new BorderPane());
            case "One":
                return new LevelOne(new Group(), ldr.loadImage("/Level 2.png"), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
            case "Two":
                return new LevelTwo(new Group(), LevelTwo.LEVEL_WIDTH, LevelTwo.LEVEL_HEIGHT); 
            default: // Google Style Guide: default for switch is mandatory
                throw new LevelNotFound(level + " is not valid for getLevel in LevelFactory.java");
        }
    }
}
