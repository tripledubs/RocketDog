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

import edu.uco.sdd.rocketdog.model.Level1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RocketDogGame extends Application {
    static final int WIDTH = 1000;
    static final int HEIGHT = 924;
    
    private GamePlayLoop gamePlayLoop;
    private Level1 currentLevel; /* Will implement as abstract class later */
    private StackPane root;
    
    @Override
    public void init() {
        gamePlayLoop = new GamePlayLoop(this);
        root = new StackPane();
        currentLevel = new Level1(root, WIDTH, HEIGHT);
    }
    
    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
        Scene scene = currentLevel;
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(scene);
        primaryStage.show();
        gamePlayLoop.start();
    }
    
    void update() {
        currentLevel.update();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}
