/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uco.sdd.rocketdog.model;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * @author Sophia
 */
public class SoundManager {
    ExecutorService soundPool = Executors.newFixedThreadPool(2);
    String soundDirectory="src/Sounds/";
    ArrayList<Sound> soundEffects;
    Media me;
    MediaPlayer mp_bg;//background
    MediaPlayer mp_am;//ambient
    MediaView mv;

    public SoundManager() {
        loadSounds();
    }

    public SoundManager(int n) {
        soundPool= Executors.newFixedThreadPool(n);
    }
    
    public void loadSounds() {
        soundEffects= new ArrayList<>();
        addSound("got_item","got_item.mp3");
        addSound("shoot","laser_shot.mp3");
        addSound("forest", "forest.mp3");
        addSound("horror", "horror.mp3");
        addSound("intense","intense.mp3");
        addSound("bg","bgmusic.mp3");
        System.out.println("Sound Files Loaded");
        for(int i=0; i<soundEffects.size();i++){
        System.out.println(soundEffects.get(i).getName());
        }
    }
    
    public void addSound(String clipName,String url) {
        if(!soundEffects.contains(clipName)){
        //add this clip to the map
        try{
            
            Sound newsound= new Sound();
            if(clipName!=null){
                newsound.setName(clipName);
                newsound.setAudioClip( new AudioClip(new File(soundDirectory+url).toURI().toString()));
                soundEffects.add(newsound);
            }
        }
        catch(Exception e){e.printStackTrace();}
	}
    }

    public void playBgSound(String source){
        me= new Media(new File(soundDirectory+source).toURI().toString());
        mp_bg= new MediaPlayer(me);
        mp_bg.setAutoPlay(true);
        mp_bg.play();
    }
    public void playBgSound(double newVolume){
        mp_bg.setVolume(newVolume);
        mp_bg.play();
    }
    public void stopBgSound(){
        mp_bg.stop();
    }
    public void shutdown(){
        soundPool.shutdown();
    }
    public void muteBgSound(boolean value){
        mp_bg.setMute(value);
    }
    public void pauseBgSound(){
        mp_bg.pause();
    }

    public void playAmSound(String source){
        me= new Media(new File(soundDirectory+source).toURI().toString());
        mp_am= new MediaPlayer(me);
        mp_am.setAutoPlay(true);
        mp_am.play();
    }
    public void playAmSound(double newVolume){
        mp_am.setVolume(newVolume);
        mp_am.play();
    }
    public void stopAmSound(){
        mp_am.stop();
    }
    public void muteAmSound(boolean value){
        mp_am.setMute(value);
    }
    public void pauseAmSound(){
        mp_am.pause();
    }
    public int searchMethod(String name){
        for(Sound sound: soundEffects){
            if(sound.getName().equalsIgnoreCase(name))
            {
                
            System.out.println("INDEX IS "+soundEffects.indexOf(sound)+sound.getName());
            return soundEffects.indexOf(sound);
            }
            else{System.out.println("inside for loop not found");}
        }
        return -1;
    }
    public void setVolume(String name,double value){
        retrieveAudioClip(name).volumeProperty().set(value);
    }
    public AudioClip retrieveAudioClip(String name){
        AudioClip audioClip= null;
        for(Sound sound: soundEffects){
            if(sound.getName().equalsIgnoreCase(name))
            {
                int i=soundEffects.indexOf(sound);
                audioClip= soundEffects.get(i).getAudioClip();
            }
        }
        return audioClip;
    }
    public void playAudioClip(String name){
//        Runnable soundPlay= new Runnable() {
//            @Override
//            public void run() {
//            }
//        };
//        soundPool.execute(soundPlay);
        retrieveAudioClip(name).play();
    }
    public void stopAudioClip(String name){
        retrieveAudioClip(name).stop();
    }
    
    public void loopAudioClip(String name){
        retrieveAudioClip(name).play();
    }

    public void deleteSoundClip(String name){
        soundEffects.remove(searchMethod(name));
        for(Sound sound:soundEffects){
            System.out.println(sound.getName());
        }
    }
    public void clearSoundEffectList(){
        soundEffects.clear();
    }
    public ArrayList<Sound> getAllSounds(){
        return soundEffects;
    }
}