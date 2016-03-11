package edu.uco.sdd.rocketdog.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Sophia
 */
public class CutSceneStage {

    Stage window;
    Stage root;
    MediaView mediaView;
    CutScenes scene1;
    String mediaURL;

    public Stage CutSceneStage(String mURL) {
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), mediaView);
        window = new Stage();
        this.setMediaURL(mURL);
        window.setAlwaysOnTop(true);
        window.setFullScreenExitHint("Press ESC");
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);

        scene1 = new CutScenes(mediaURL, 5000);
        this.setMediaView(scene1.getMediaView());

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: Black");
        mediaView.setFitWidth(1000);
        mediaView.setFitHeight(924);
        mediaView.setPreserveRatio(false);

        borderPane.setCenter(mediaView);

        Scene scene = new Scene(borderPane, 1000, 924);
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });
        window.setScene(scene);
        return window;

    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Stage getRoot() {
        return root;
    }

    public void setRoot(Stage root) {
        this.root = root;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
    }

    public CutScenes getScene1() {
        return scene1;
    }

    public void setScene1(CutScenes scene1) {
        this.scene1 = scene1;
    }

    private void close() {
        window.close();
    }

}
