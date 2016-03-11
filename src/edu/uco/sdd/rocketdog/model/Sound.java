/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;

import java.net.URL;
import javafx.scene.media.AudioClip;


/**
 *
 * @author Sophia
 */
public class Sound {
    String name;
    AudioClip audioClip;
    boolean foundClip=false;
    
    public Sound() {
        this.audioClip= null;
        this.name= null;
    }

    public Sound(String name, AudioClip audioClip) {
        this.name = name;
        this.audioClip = audioClip;
    }

    public Sound(String name, String soundFileName) {
        this.name = name;
        // Use URL (instead of File) to read from disk and JAR.
         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         this.audioClip= new AudioClip(url.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AudioClip getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(AudioClip audioClip) {
        this.audioClip = audioClip;
    }
    
}
