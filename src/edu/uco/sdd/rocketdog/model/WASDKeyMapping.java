package edu.uco.sdd.rocketdog.model;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WASDKeyMapping implements KeyMapping {

    @Override
    public void handleKeyPressed(KeyEvent keyEvent, int speed, Level1 level) {
        if (speed != 0) {
            switch (keyEvent.getCode()) {
                case A:
                    level.rd.getSprite().setScaleX(-1);
                    level.rd.setVelX(-speed);
                    break; //rd.x -= 10;         
                case D:
                    level.rd.getSprite().setScaleX(1);
                    level.rd.setVelX(speed);
                    break; //rd.x +=  10;
                case W:
                    level.rd.setVelY(-speed);
                    break; //rd.y -= -10; 
                case S:
                    level.rd.setVelY(speed);
                    break; //rd.y +=  10; 
                case F1:
                    level.root.getChildren().remove(1);
                    level.rd = new RocketDog(new SpitzIdleAnimateStrategy(), level.rd.getPosition());
                    level.rd.addEntityClass(level.player, 1);
                    level.rd.setPosition(level.rd.getPosition());
                    level.rd.getSprite().setTranslateX(level.rd.getPosition().getX());
                    level.rd.getSprite().setTranslateY(level.rd.getPosition().getY());
                    level.root.getChildren().add(1, level.rd.getSprite());
                    break;

                case F2:
                    level.root.getChildren().remove(1);
                    level.rd = new RocketDog(new SpitzDeadAnimateStrategy(), level.rd.getPosition());
                    level.rd.addEntityClass(level.player, 1);
                    level.rd.setPosition(level.rd.getPosition());
                    level.rd.getSprite().setTranslateX(level.rd.getPosition().getX());
                    level.rd.getSprite().setTranslateY(level.rd.getPosition().getY());
                    level.root.getChildren().add(1, level.rd.getSprite());
                    break;
                case H:
                    if (level.visibleHitboxes) {
                        level.visibleHitboxes = false;
                    } else {
                        level.visibleHitboxes = true;
                    }
                    break;
                case O:
                    Stage optionStage = new Stage();

                    GridPane grid = new GridPane();

                    Scene optionScene = new Scene(grid, 500, 500);
                    optionStage.initModality(Modality.APPLICATION_MODAL);
                    optionStage.setScene(optionScene);
                    optionStage.setTitle("Change KeyBindings");
                    //Creating a GridPane container
                    grid.setPadding(new Insets(10, 10, 10, 10));
                    grid.setVgap(5);
                    grid.setHgap(5);
                    //Defining the Name text field
                    final Button defaultButton = new Button("Default KeyMapping");
                    final Button wasdButton = new Button("WASD KeyMapping");
                    GridPane.setConstraints(defaultButton, 40, 0);

                    defaultButton.setDisable(true);
                    //Defining the Last Name text field
                    GridPane.setConstraints(wasdButton, 40, 1);

                    if (level.keyMappingContext.getClass().getName().compareTo("DefaultKeyMapping") == 0) {
                        defaultButton.setDisable(true);
                        wasdButton.setDisable(false);
                    } else {
                        wasdButton.setDisable(true);
                        defaultButton.setDisable(false);
                    }

                    defaultButton.setOnAction((ActionEvent) -> {
                        level.keyMappingContext.setDefaultKeyMapping();
                        defaultButton.setDisable(true);
                        wasdButton.setDisable(false);
                    });

                    wasdButton.setOnAction((ActionEvent) -> {
                        level.keyMappingContext.setWASDKeyMapping();
                        wasdButton.setDisable(true);
                        defaultButton.setDisable(false);
                    });
                    Button close = new Button("Close");
                    close.setOnAction((ActionEvent) -> {
                        optionStage.close();
                    });
                    GridPane.setConstraints(close, 40, 2);
                    grid.getChildren().add(close);
                    grid.getChildren().add(wasdButton);
                    grid.getChildren().add(defaultButton);
                    optionStage.show();
                    break;
            }
        } else {
            switch (keyEvent.getCode()) {
                case A:
                    level.rd.getSprite().setScaleX(-1);
                    level.rd.setVelX(0);
                    break; //rd.x -= 10;         
                case D:
                    level.rd.getSprite().setScaleX(1);
                    level.rd.setVelX(0);
                    break; //rd.x +=  10;
                case W:
                    level.rd.setVelY(0);
                    break; //rd.y -= -10; 
                case S:
                    level.rd.setVelY(0);
                    break; //rd.y +=  10; 
            }
        }
    }

}
