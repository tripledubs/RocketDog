package edu.uco.sdd.rocketdog.controller;

import javafx.animation.AnimationTimer;


public class GamePlayLoop extends AnimationTimer {
    
    public RocketDogGame game;
    
    GamePlayLoop(RocketDogGame game) {
        this.game = game;
    }
    
    @Override
    public void handle(long now) {
        game.update();
    }

    @Override
    public void start(){
        super.start();
    }
    @Override
    public void stop(){
        super.stop();
    }
}
