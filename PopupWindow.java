package com.example.OnlineShop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PopupWindow {

    public static void showPopup(String title, String message) {
        // Create a new stage for the popup window
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);

        // Create a message label and a close button
        javafx.scene.control.Label label = new javafx.scene.control.Label(message);
        Button closeButton = new Button("بستن");
        closeButton.setOnAction(e -> popupStage.close());

        // Create a layout for the popup window
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        // Create a scene and set the layout
        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);

        // Show the popup window after 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> popupStage.showAndWait());
        delay.play();
    }
}
