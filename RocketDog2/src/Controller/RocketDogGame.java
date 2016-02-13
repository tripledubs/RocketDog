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
package Controller;

import Model.Level1;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RocketDogGame extends Application {
    static final int WIDTH = 640;
    static final int HEIGHT = 400;
    boolean up, down, left, right;
    StackPane root;
    Scene scene;
    Image splashScreen,instructionLayer, scoresLayer;
    // ImageView Backplate: will contain all artwork for splashscreen
    // ImageView splashscreen textarea will contain composite images
    // simulate four different scenes using imageview scene graph node objective
    ImageView splashScreenBackplate, splashScreenTextArea;
    Button startButton, exitButton, helpButton, optionButton,scoreButton;//
    //because a button cannot be positioned individually we use HBOX, Insets, and pos to contain and position all buttons
    HBox buttonContainer;// contain all buttons needed 
    Insets buttonContainerPadding;
    
    
    private GamePlayLoop gamePlayLoop;
    private Level1 currentLevel; /* Will implement as abstract class later */
    
    
    @Override
    public void init() {
        gamePlayLoop = new GamePlayLoop(this);
        //root = new StackPane();
        //currentLevel = new Level1(root, WIDTH, HEIGHT);
    }
    
    /* This is the starting point for JavaFX applications */
    @Override
    public void start(Stage primaryStage) {
        
        createSplashScreneNode();
        //method to add nodes to StackPane root node
        addNodeToStackPane();
        /*  Stage and Scene must be constructed from within the start method */
        //scene = currentLevel;
        
        primaryStage.setTitle("Rocket Dog!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        startButton.setOnAction((ActionEvent e)->{
                splashScreenBackplate.setVisible(false);
                splashScreenTextArea.setVisible(false);
        });
        exitButton.setOnAction((ActionEvent e)->{
            //exit game
        });
        optionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        helpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                splashScreenTextArea.setImage(instructionLayer);
            }
        });
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                splashScreenTextArea.setImage(scoresLayer);//will print the scores
            }
        });
        gamePlayLoop.start();
        
        
    }
    
    void update() {
        //currentLevel.update();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createSplashScreneNode() {
        root=new StackPane();
        scene=new Scene(root, WIDTH, HEIGHT,Color.WHITE);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                switch(e.getCode()){
                    case UP: up=true;break;
                    case W: up=true;break;
                    case DOWN:down=true;break;
                    case S: down=true; break;
                    case LEFT: left=true;break;
                    case A: left=true;break;
                    case RIGHT:right=true;break;
                    case D: right=true; break;
                }
                }
        });
        scene.setOnKeyReleased((KeyEvent event)->{
            switch(event.getCode()){
                    case UP: up=false;break;
                    case W: up=false;break;
                    case DOWN: down=false;break;
                    case S: down=false; break;
                    case LEFT: left=false;break;
                    case A: left=false;break;
                    case RIGHT: right=false;break;
                    case D: right=false; break;
                }
        });
        
        buttonContainer=new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding=new Insets(0,0,10,16);
        buttonContainer.setPadding(buttonContainerPadding);
        
        startButton=new Button();
        startButton.setText("Play Game");
        exitButton=new Button();
        exitButton.setText("Exit");
        helpButton=new Button();
        helpButton.setText("How to Play");
        optionButton=new Button();
        optionButton.setText("Options");
        
        buttonContainer.getChildren().addAll(startButton,helpButton,optionButton, exitButton);
        
        //image(url, width,height, boolean preserveratio, boolean smoothing, boolean preload)
        
        splashScreen=new Image("/splashscreenbg.png", 640,400, true, false, true);
        splashScreenBackplate=new ImageView();
        splashScreenBackplate.setImage(splashScreen);
        //instruction layer 
        
        instructionLayer=new Image("/instructionsbg.png", 640,400,true, false,true);
        splashScreenTextArea=new ImageView();
        splashScreenTextArea.setImage(instructionLayer);
        scoresLayer=new Image("/score.png",640, 400, true, false,true);
        
    }

    private void addNodeToStackPane() {
        // add the nodes created to scene graph which is stackpane
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        root.getChildren().add(buttonContainer);
        //the first node added is the bottom layer , the instruction layer goes right on top, after this we add the button container
        // no need to add the button to the stackPane since it has already been done.
        
    }


}
