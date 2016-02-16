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
import edu.uco.sdd.rocketdog.model.WASDKeyMapping;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RocketDogGame extends Application {

    static final int GAME_SCREEN_WIDTH = 1000;
    static final int GAME_SCREEN_HEIGHT = 924;

    private GamePlayLoop gamePlayLoop;
    private Level currentLevel; /* Will implement as abstract class later */

    private StackPane root;

    private Insets buttonContainerPadding;
    private ImageView splashScreenBackplate, splashScreenTextArea;
    private Image splashScreenbg, instructionsLayer, optionsLayer, scoresLayer;
    private static HBox buttonContainer;
    private Button startButton, instructionsButton, optionsButton, scoresButton,
            exitButton, optionsCloseButton, optionsDefaultButton, optionsWASDButton;

    @Override
    public void init() {
        root = new StackPane();
        gamePlayLoop = new GamePlayLoop(this);
        currentLevel = new LevelOne(root, new ImageView(new Image("/Level 2.png")), GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);

        splashScreenTextArea = new ImageView();
        splashScreenBackplate = new ImageView();
        buttonContainer = new HBox(12);// Horizontal box holding all buttons
        buttonContainerPadding = new Insets(0, 0, 10, 16);

        startButton = new Button("Play");
        instructionsButton = new Button("Instructions");
        optionsButton = new Button("Options");
        scoresButton = new Button("Scores");
        optionsCloseButton = new Button("Close");
        optionsDefaultButton = new Button("Default KeyMapping");
        optionsWASDButton = new Button("WASD KeyMapping");

        optionsDefaultButton.setDisable(true);
    }

    private void loadImages() {
        // load all needed images
        splashScreenbg = new Image("/splashscreenbg.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
        instructionsLayer = new Image("/instruct.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, false, true);
        optionsLayer = new Image("/options.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
        scoresLayer = new Image("/scores.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
    }

    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
        root = new StackPane();
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(new Scene(root, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
        primaryStage.show();

        loadImages();

        /**
         * *****************START APPLICATION******************
         */
        startButton.setOnAction((ActionEvent) -> {
            if (!optionsDefaultButton.isDisable()) {
                currentLevel.getKeyMapping().setKeyMapping(new WASDKeyMapping());
            }
            primaryStage.setScene(currentLevel);
            primaryStage.show();
        });

        /**
         * *****************EXIT APPLICATION******************
         */
        exitButton = new Button("Exit");
        exitButton.setOnAction((ActionEvent) -> {
            primaryStage.close();
        });
        
        /**
         * *****************GAME INSTRUCTIONS******************
         */
        instructionsButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionsLayer);

        });
        
        /**
         * *****************KEYBOARD KEY MAPPING******************
         */
        optionsButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(optionsLayer);
            displayOptionsScreen();
        }
        );
        
        /**
         * *****************SCORES BOARD******************
         */
        scoresButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(scoresLayer);
        }
        );

        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(startButton, instructionsButton, optionsButton, scoresButton, exitButton);
        splashScreenBackplate.setImage(splashScreenbg);

        root.getChildren().add(buttonContainer);
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);

        gamePlayLoop.start();
    }

    public void displayOptionsScreen() {
        GridPane grid = new GridPane();
        Scene optionScene = new Scene(grid, 500, 500);
        Stage optionStage = new Stage();

        optionStage.initModality(Modality.APPLICATION_MODAL);
        optionStage.setScene(optionScene);
        optionStage.setTitle("Change KeyBindings");

        //Creating a GridPane container
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        GridPane.setConstraints(optionsCloseButton, 42, 2);
        GridPane.setConstraints(optionsDefaultButton, 40, 0);
        GridPane.setConstraints(optionsWASDButton, 40, 1);

        optionsDefaultButton.setOnAction((ActionEvent) -> {
            optionsDefaultButton.setDisable(true);
            optionsWASDButton.setDisable(false);
        });

        optionsWASDButton.setOnAction((ActionEvent) -> {
            optionsWASDButton.setDisable(true);
            optionsDefaultButton.setDisable(false);
        });

        optionsCloseButton.setOnAction((ActionEvent) -> {
            optionStage.close();
        });

        grid.getChildren().add(optionsCloseButton);
        grid.getChildren().add(optionsDefaultButton);
        grid.getChildren().add(optionsWASDButton);

        optionStage.show();
    }

    public void update() {
        currentLevel.update();
    }

    public GamePlayLoop getGamePlayLoop() {
        return gamePlayLoop;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
