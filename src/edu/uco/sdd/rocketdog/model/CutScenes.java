package edu.uco.sdd.rocketdog.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.uco.sdd.rocketdog.model.CutSceneStage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Sophia
 */
public class CutScenes extends CutSceneStage {
    File file;
    String source;
    Media media;
    MediaPlayer mediaPlayer;// = new MediaPlayer(media);
    MediaView mediaView;
    Timer timer;
    TimerTask task;
    
    public CutScenes(String url, long delay){
        timer= new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                timer.purge();
                }
        };
        this.setSource(new File(url).toURI().toString());
        this.setMedia(new Media(source));
        this.setMediaPlayer(new MediaPlayer(media));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(Double.MAX_VALUE);
        this.setMediaView(new MediaView(mediaPlayer));
        timer.schedule(task, delay);// can be changed depending on cutscene lenght
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String s) {
        this.source = s;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
    
    
}
