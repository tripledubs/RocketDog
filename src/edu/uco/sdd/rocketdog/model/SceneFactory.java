package edu.uco.sdd.rocketdog.model;

import Exceptions.LevelNotFound;
import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_HEIGHT;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_WIDTH;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;

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
                return new SplashLevel(new StackPane());
            case "One":
                return new LevelOne(new Group(), ldr.loadImage("/Level 2.png"), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
            case "introScene":
                return new CutSceneIntro(new BorderPane());
        }
        throw new LevelNotFound(level + " is not valid for getLevel in LevelFactory.java");
    }
}
