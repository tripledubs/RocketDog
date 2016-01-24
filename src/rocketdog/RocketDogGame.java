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
package rocketdog;

import rocketdog.Actors.RocketDog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Dubs
 */
public class RocketDogGame extends Application {
    
    RocketDog rd;
    private GamePlayLoop gamePlayLoop;
    private Image lvl1Image;
    private ImageView level1ImageView;
    
    @Override
    public void init() {
        rd = new RocketDog();
        rd.y = 300;
        rd.x = -300;
        gamePlayLoop = new GamePlayLoop(this);
        gamePlayLoop.start();
        lvl1Image = new Image("/Level 2.png",1000,924,false,false);
        level1ImageView = new ImageView(lvl1Image);
    }
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getChildren().add(level1ImageView);
        root.getChildren().add(rd.spriteFrame);
        Scene scene = new Scene(root, 1000, 924);
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:  rd.x-=10; break;
                case RIGHT: rd.x+=10; break;
                case UP: rd.y-=10; break;
                case DOWN: rd.y+=10; break;
            }  
        });
        
        primaryStage.setTitle("Rocket Dog!!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
