package edu.uco.sdd.rocketdog.model;

import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_HEIGHT;
import static edu.uco.sdd.rocketdog.controller.RocketDogGame.GAME_SCREEN_WIDTH;
import edu.uco.sdd.rocketdog.controller.WASDKeyMapping;
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

public class SplashLevel {

    private final ImageView splashScreenTextArea;
    private final ImageView splashScreenBackplate;
    private final HBox buttonContainer;
    private final Insets buttonContainerPadding;
    private final Button startButton;
    private final Button instructionsButton;
    private final Button optionsButton;
    private final Button scoresButton;
    private final Button optionsCloseButton;
    private final Button optionsDefaultButton;
    private final Button optionsWASDButton;
    private Image splashScreenbg;
    private Image instructionsLayer;
    private Image optionsLayer;
    private Image scoresLayer;
    private StackPane root;
    private final Button exitButton;

    public SplashLevel() {

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
        loadImages();

        /**
         * *****************START APPLICATION******************
         */
        startButton.setOnAction((ActionEvent) -> {
            if (!optionsDefaultButton.isDisable()) {
                System.out.println("Start button clicked");

            }
        });

        /**
         * *****************EXIT APPLICATION******************
         */
        exitButton = new Button("Exit");
        exitButton.setOnAction((ActionEvent) -> {
            System.out.println("Quitting!");
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

    private void loadImages() {
        // load all needed images
        splashScreenbg = new Image("/splashscreenbg.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
        instructionsLayer = new Image("/instruct.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, false, true);
        optionsLayer = new Image("/options.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
        scoresLayer = new Image("/scores.png", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, true, true, true);
    }

}
