package com.example.OnlineShop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;



import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;


public final class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {


        Database.readPerson(Information.getPersons());

        Database.readProduct(Information.getProducts());

        Database.readTransaction(Information.getTransactions());

        wareHouse wareHouse = new wareHouse("FUM WareHouse", "Mashhad", "sina1382");
        Information.getWareHouses().add(wareHouse);

        Information.getPersons().get(1).setMoney(1000000);


//        for(int i = 0;i<Information.getProducts().size();i++){
//            String address = "E:\\java\\OnlineShop\\src\\main\\resources\\com\\example\\image\\";
//            address+=(i+1)+".jpg";
//            Information.getProducts().get(i).setAddressImage(address);
//            Database.updateProduct(Information.getProducts().get(i));
//        }








        VBox loadingBox = new VBox();
        loadingBox.setAlignment(Pos.CENTER);

        // Create the loading label
        Label loadingLabel = new Label("درحال انتقال به فروشگاه...");
        loadingLabel.setStyle("-fx-font-size: 24px;");

        // Create the progress indicator for the loading animation
        ProgressIndicator loadingIndicator = new ProgressIndicator();

        // Add the label and the progress indicator to the loading box
        loadingBox.getChildren().addAll(loadingLabel, loadingIndicator);

        // Create a PauseTransition for the loading duration (5 seconds)
        PauseTransition loadingAnimation = new PauseTransition(Duration.seconds(5));
        loadingAnimation.setOnFinished(event -> {
            try {
                // Load and show the main page after the loading animation
                Scene scene = new Scene(Methods.loader("sellerAccount.fxml").load(), 500, 600);
                Methods.stage.setScene(scene);
                Methods.stage.setFullScreen(true);
                Methods.stage.show();

                // Close the loading animation page
                primaryStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Play the loading animation
        loadingAnimation.play();

        // Create the loading scene and set it as the primaryStage scene
        Scene loadingScene = new Scene(loadingBox);
        primaryStage.setScene(loadingScene);

        // Set the primaryStage to full screen
        primaryStage.setFullScreen(true);

        // Show the primaryStage
        primaryStage.show();
    }





















    public static void main(String[] args) {
        launch();
    }










}