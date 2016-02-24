package edu.uco.sdd.rocketdog.model;

import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_HEIGHT;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_WIDTH;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelFactory {
    protected String level;
    private Group root;
    
    public LevelFactory(String level) {
        this.level = level;
    }
    
    public ILevel getLevel() {
        switch (level) {
            case "splash":
                return new SplashLevel();
            case "one":
                return new LevelOne(root, new ImageView(new Image("/Level 2.png")), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
        }
        return null;
    }
    
}
