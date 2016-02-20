package edu.uco.sdd.rocketdog.controller;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class ImageViewLoader {

    private static final ImageViewLoader INSTANCE = new ImageViewLoader();

    private ImageViewLoader() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instanciated");
        }
    }

    public static ImageViewLoader getInstance() {
        return INSTANCE;
    }

    // Load this singleton and we never have to see the catch
    // try blocks again. 
    public ImageView loadImage(String imgFile) {
        Image img = null;
        try {
            img = new Image(imgFile);
        } catch (IllegalArgumentException exc) {
            System.out.println("'" + imgFile + "'" + " not found, exiting...");
            StackTraceElement[] traces = Thread.currentThread().getStackTrace();
            System.out.println(traces[2]);
            Platform.exit();
        }
        return new ImageView(img);
    }
    
}