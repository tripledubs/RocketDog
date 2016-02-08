package edu.uco.sdd.rocketdog.model;

import javafx.scene.image.ImageView;

interface IAnimateStrategy {
    void handle();
    ImageView getSprite();
}
