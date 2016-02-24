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

import edu.uco.sdd.rocketdog.model.Level;
import edu.uco.sdd.rocketdog.model.LevelOne;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RocketDogGame extends Application {
    public static final int GAME_SCREEN_WIDTH = 800 ; 
    public static final int GAME_SCREEN_HEIGHT = 600 ;
    private Level currentLevel; 
    private Pane root;
    private GamePlayLoop gamePlayLoop;

    @Override
    public void init() {
        root = new Pane();
        gamePlayLoop = new GamePlayLoop(this);
        currentLevel = new LevelOne(root, new ImageView(new Image("/Level 2.png")), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
    }


    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
        root = new StackPane();
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(currentLevel);
        primaryStage.show();


    }

    public void update() {
        currentLevel.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
