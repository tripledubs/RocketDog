/*
 Copyright 2016
 Noah Sefcik, Richard Dobie, Spencer Harris, Sophia Msaaf
 Dwayne Page, Kody Strickland	

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 OTHER DEALINGS IN THE SOFTWARE.

 Background - Designed by Bilal-khan - Freepik.com
 */
package edu.uco.sdd.rocketdog.controller;

import edu.uco.sdd.rocketdog.model.ILevel;
import edu.uco.sdd.rocketdog.model.Level;
import edu.uco.sdd.rocketdog.model.LevelFactory;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RocketDogGame extends Application {
    public static final int GAME_SCREEN_WIDTH = 800 ; 
    public static final int GAME_SCREEN_HEIGHT = 600 ;
    private Parent root;
    private GamePlayLoop gamePlayLoop;
    private Scene currentLevel;
    private ILevel updateableLevel;
    Stage currentStage;
    
    LevelFactory lf;
    String[] levels;
    int levelIndex;

    @Override
    public void init() {
        gamePlayLoop = new GamePlayLoop(this);
        levels = new String[]{
            "Splash",
            "One"
        };
        levelIndex = 1;
        lf = new LevelFactory(levels[levelIndex]);
        currentLevel = lf.getLevel();
        updateableLevel = (ILevel) currentLevel;
        
    }


    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
        root = lf.getRoot(levels[0]);
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(currentLevel);
        primaryStage.show();
        gamePlayLoop.start();
    }

    public void update() {
        while (!updateableLevel.isDone()) {
            updateableLevel.update();
        }
        String newLevel = levels[++levelIndex];
        lf = new LevelFactory(levels[++levelIndex]);
        currentStage.setScene(lf.getLevel());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
