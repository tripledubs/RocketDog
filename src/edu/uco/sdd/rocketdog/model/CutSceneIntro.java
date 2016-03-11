package edu.uco.sdd.rocketdog.model;

import edu.uco.sdd.rocketdog.controller.ImageViewLoader;
import java.io.File;
import java.net.URI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CutSceneIntro extends Scene implements ILevel {
    Media video;
    MediaPlayer mp;
    MediaView mv;
    boolean isDone;
    HBox hbox;
    Text t;

    public CutSceneIntro(BorderPane root) {
        super(root,800,500);
        isDone = false;

        // Set up the bottom of the border pane
        hbox = new HBox(3);
        t = new Text(10,10,"Credits: Spencer, Noah, Sophia, Richard, Dwayne");
        t.setFont(new Font(20));
        hbox.getChildren().add(t);
        root.setBottom(hbox);
        
        String fileToPlay = new File("src").toURI().toString() + "splashReduced.mp4";
        video = new Media(fileToPlay);
        mp = new MediaPlayer(video);
        mv = new MediaView(mp);
        mp.setAutoPlay(true);
        
        // Set isDone when done
        Runnable task = () -> {
            isDone = true;
        };
        mp.setOnEndOfMedia(task);
        
        // Add to Scene
        root.getChildren().add(mv);
    }
    
    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void update() {
    }
    
}
