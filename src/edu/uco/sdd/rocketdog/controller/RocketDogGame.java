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
import java.awt.SplashScreen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RocketDogGame extends Application {
    static final int WIDTH = 1000;
    static final int HEIGHT = 924;
    static HBox buttonContainer;
    Button startButton, instructionsButton,optionsButton,scoresButton, exitButton;  
    //static final List<KeyBinding> COMBO_BOX_BINDINGS= new ArrayList<>();
    Scene scene;
    Insets buttonContainerPadding;
    ImageView splashScreenBackplate,splashScreenTextArea ;
    Image splashScreenbg, instructionsLayer, optionsLayer ,scoresLayer;
    private GamePlayLoop gamePlayLoop;
    
    private Level1 currentLevel; /* Will implement as abstract class later */
    private StackPane root;
    
    @Override
    public void init() {
        gamePlayLoop = new GamePlayLoop(this);
        root = new StackPane();
        currentLevel = new Level1(root, WIDTH, HEIGHT);
        //splashScreen=new SplashScreen(root, WIDTH, WIDTH);
    }
    
    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        /*  Stage and Scene must be constructed from within the start method */
        
        primaryStage.setTitle("Rocket Dog!");
        root= new StackPane();
        Group game= new Group();
        splashScreenTextArea=new ImageView();
        splashScreenBackplate=new ImageView();
        
        root.getChildren().add(game);
        primaryStage.setScene(new Scene(root, WIDTH,HEIGHT));
        primaryStage.show();
        
        buttonContainer= new HBox(12);// Horizontal box holding all buttons
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainerPadding=new Insets(0,0,10,16);
        buttonContainer.setPadding(buttonContainerPadding); 
        loadImages();
        startButton= new Button();
        startButton.setText("Play");
        startButton.setOnAction((ActionEvent)->{
                System.out.println("Start game called");
                primaryStage.setScene(currentLevel);
                primaryStage.show();
        });
        /*******************EXIT APPLICATION*******************/
        exitButton=new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction( (ActionEvent)->{
                System.out.println("Exit window");
                primaryStage.close();
            
        });
        /*******************GAME INSTRUCTIONS*******************/
        instructionsButton=new Button();
        instructionsButton.setText("Instructions");
        instructionsButton.setOnAction( (ActionEvent )->{
                System.out.println("instructions displayed!");
                splashScreenBackplate.setVisible(true);
                splashScreenTextArea.setVisible(true);
                splashScreenTextArea.setImage(instructionsLayer);
            
        });
        /*******************KEYBOARD KEY MAPPING*******************/
        optionsButton=new Button();
        optionsButton.setText("Options");
        optionsButton.setOnAction( (ActionEvent )->{
                System.out.println("options layer displayed");
                splashScreenBackplate.setVisible(true);
                splashScreenTextArea.setVisible(true);
                splashScreenTextArea.setImage(optionsLayer);
            }
        );
        /*******************SCORES BOARD*******************/
        scoresButton=new Button();
        scoresButton.setText("Scores");
        scoresButton.setOnAction( (ActionEvent)->{
                System.out.println("ScoresLayer displayed!");
                splashScreenBackplate.setVisible(true);
                splashScreenTextArea.setVisible(true);
                splashScreenTextArea.setImage(scoresLayer);
            }
        );
        buttonContainer.getChildren().addAll(startButton,optionsButton, scoresButton ,exitButton);
        
        splashScreenBackplate.setImage(splashScreenbg);
        
        
        
        root.getChildren().add(buttonContainer);
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        
        
       
        gamePlayLoop.start();
        }
    
    private void loadImages() {
        // load all needed images
        splashScreenbg=new Image("/splashscreenbg.png", WIDTH, HEIGHT, true, true, true);
        System.out.println("/splashscreenbg.png");
        instructionsLayer=new Image("/instruct.png",WIDTH, HEIGHT, true,false,true);
        optionsLayer=new Image("/options.png", WIDTH, HEIGHT, true, true, true);
        scoresLayer=new Image("/scores.png", WIDTH, HEIGHT, true, true, true);
        
        
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
