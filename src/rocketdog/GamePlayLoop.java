/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocketdog;

import javafx.animation.AnimationTimer;
import rocketdog.Actors.RocketDog;

/**
 *
 * @author Dubs
 */
public class GamePlayLoop extends AnimationTimer {
    protected RocketDogGame game;
    public GamePlayLoop(RocketDogGame mygame){
        game = mygame;
    }
    @Override
    public void handle(long now) {
        game.rd.update();
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
