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

import edu.uco.sdd.rocketdog.model.DefaultKeyMapping;
import edu.uco.sdd.rocketdog.model.KeyMapping;
import edu.uco.sdd.rocketdog.model.KeyMappingContext;
import edu.uco.sdd.rocketdog.model.Level1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RocketDogGame extends Application {

    static final int WIDTH = 1000;
    static final int HEIGHT = 924;

    private GamePlayLoop gamePlayLoop;
    public Level1 currentLevel; /* Will implement as abstract class later */

    private StackPane root;
    public KeyMappingContext keyMappingContext;
    public KeyMapping keyMapping;

    Insets buttonContainerPadding;
    ImageView splashScreenBackplate, splashScreenTextArea;
    Image splashScreenbg, instructionsLayer, optionsLayer, scoresLayer;
    static HBox buttonContainer;
    Button startButton, instructionsButton, optionsButton, scoresButton, exitButton;

    @Override
    public void init() {
        keyMappingContext = new KeyMappingContext();
        keyMappingContext.setKeyMapping(new DefaultKeyMapping());
        gamePlayLoop = new GamePlayLoop(this);
        root = new StackPane();
        currentLevel = new Level1(root, WIDTH, HEIGHT, keyMappingContext);
    }

    private void loadImages() {
        // load all needed images
        splashScreenbg = new Image("/splashscreenbg.png", WIDTH, HEIGHT, true, true, true);
        System.out.println("/splashscreenbg.png");
        instructionsLayer = new Image("/instruct.png", WIDTH, HEIGHT, true, false, true);
        optionsLayer = new Image("/options.png", WIDTH, HEIGHT, true, true, true);
        scoresLayer = new Image("/scores.png", WIDTH, HEIGHT, true, true, true);

    }

    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
//        Scene scene = currentLevel;
//        primaryStage.setTitle("Rocket Dog!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        gamePlayLoop.start();
        root = new StackPane();
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
        splashScreenTextArea = new ImageView();
        splashScreenBackplate = new ImageView();

        buttonContainer = new HBox(12);// Horizontal box holding all buttons
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        loadImages();
        startButton = new Button("Play");
        startButton.setOnAction((ActionEvent) -> {
            System.out.println("Start game called");
            primaryStage.setScene(currentLevel);
            primaryStage.show();
        });
        /**
         * *****************EXIT APPLICATION******************
         */
        exitButton = new Button("Exit");
        exitButton.setOnAction((ActionEvent) -> {
            System.out.println("Exit window");
            primaryStage.close();

        });
        /**
         * *****************GAME INSTRUCTIONS******************
         */
        instructionsButton = new Button("Instructions");
        instructionsButton.setOnAction((ActionEvent) -> {
            System.out.println("instructions displayed!");
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionsLayer);

        });
        /**
         * *****************KEYBOARD KEY MAPPING******************
         */
        optionsButton = new Button("Options");
        optionsButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(optionsLayer);
            displayOptionsScreen(primaryStage);
        }
        );
        /**
         * *****************SCORES BOARD******************
         */
        scoresButton = new Button("Scores");
         scoresButton.setOnAction((ActionEvent) -> {
         System.out.println("ScoresLayer displayed!");
         splashScreenBackplate.setVisible(true);
         splashScreenTextArea.setVisible(true);
         splashScreenTextArea.setImage(scoresLayer);
         }
         );
        buttonContainer.getChildren().addAll(startButton, instructionsButton, optionsButton, scoresButton, exitButton);
        splashScreenBackplate.setImage(splashScreenbg);
        root.getChildren().add(buttonContainer);
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        gamePlayLoop.start();
    }

    private void displayOptionsScreen(Stage MainWindow) {
        Stage optionStage = new Stage();

        GridPane grid = new GridPane();

        Scene optionScene = new Scene(grid, 500, 500);
        optionStage.initModality(Modality.APPLICATION_MODAL);
        optionStage.setScene(optionScene);
        optionStage.setTitle("Change KeyBindings");
        //Creating a GridPane container
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        //Defining the Name text field
        final TextField moveUp = new TextField();
        moveUp.setPromptText("UP");
        moveUp.setPrefColumnCount(10);
        moveUp.getText();
        GridPane.setConstraints(moveUp, 0, 0);
        grid.getChildren().add(moveUp);
        //Defining the Last Name text field
        final TextField moveDown = new TextField();
        moveDown.setPromptText("Down");
        GridPane.setConstraints(moveDown, 0, 1);
        grid.getChildren().add(moveDown);

        Button close = new Button("Close");
        close.setOnAction((ActionEvent) -> {
            optionStage.close();
        });
        GridPane.setConstraints(close, 1, 0);
        grid.getChildren().add(close);
        optionStage.show();
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
