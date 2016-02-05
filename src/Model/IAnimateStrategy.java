package Model;

import javafx.scene.image.ImageView;

interface IAnimateStrategy {
    void handle();
    ImageView getSprite();
}
