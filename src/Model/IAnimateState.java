package Model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

interface IAnimateState {
    void handle(ImageView sprite, Rectangle2D[] frames);
}
