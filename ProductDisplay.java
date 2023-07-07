package com.example.OnlineShop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProductDisplay extends Application {

    private static final int MAX_RATING = 5;
    private Rectangle[] ratingStars;
    private int selectedRating = 0;
    private double averageRating = 0.0;
    private int ratingCount = 0;

    @Override
    public void start(Stage primaryStage) {
        // Create a BorderPane as the root container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #794caf;");
        root.setPadding(new Insets(10));

        // Create a VBox layout for the left side (product information)
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        // Create an ImageView for the product image
        String imagePath = "file:///C:\\Users\\ASUS\\Downloads\\pofac.jpg";
        Image productImage = new Image(imagePath);
        ImageView imageView = new ImageView(productImage);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        // Create labels for product information
        Label nameLabel = new Label("چیتوز حلقه ای");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label descriptionLabel = new Label("خیلی گرده");
        descriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label priceLabel = new Label("قیمت : 23000");
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label averageRatingLabel = new Label("میانگین رتبه: " + String.format("%.2f", averageRating));
        averageRatingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Create a star rating system
        ratingStars = new Rectangle[MAX_RATING];
        for (int i = 0; i < MAX_RATING; i++) {
            Rectangle star = new Rectangle(20, 20, Color.TRANSPARENT);
            star.setStroke(Color.BLACK);
            star.setStrokeWidth(2);
            star.setArcWidth(6);
            star.setArcHeight(6);
            int rating = i + 1;
            star.setOnMouseClicked(e -> {
                selectRating(rating);
                updateRatingStars();
            });
            ratingStars[i] = star;
        }

        // Create an HBox for the star rating system
        HBox ratingBox = new HBox(5);
        ratingBox.setAlignment(Pos.CENTER_LEFT);
        ratingBox.getChildren().addAll(ratingStars);

        // Create the "امتیاز دادن" button
        Button addToCartButton = new Button("افزودن به سبد خرید");
        addToCartButton.setStyle("-fx-background-color: #4c9daf; -fx-text-fill: white; -fx-font-weight: bold;");
        addToCartButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        addToCartButton.setOnAction(e -> {
            // Perform the necessary actions when the "امتیاز دادن" button is clicked
            System.out.println("Added to Cart");
        });

        // Add the image, labels, and rating to the VBox
        vbox.getChildren().addAll(imageView, nameLabel, descriptionLabel, priceLabel, ratingBox, addToCartButton);

        // Create a VBox for the right side (comment section)
        VBox commentVBox = new VBox(10);
        commentVBox.setAlignment(Pos.CENTER);
        commentVBox.setPadding(new Insets(10));
        commentVBox.setStyle("-fx-background-color: #ffffff;");

        // Create a GridPane for the comment section
        GridPane commentGridPane = new GridPane();
        commentGridPane.setVgap(10);

        // Create a ScrollPane for the comment section
        ScrollPane commentScrollPane = new ScrollPane(commentGridPane);
        commentScrollPane.setFitToWidth(true);
        commentScrollPane.setPrefViewportHeight(300);

        // Create a TextArea for entering comments
        TextArea commentTextArea = new TextArea();
        commentTextArea.setPromptText("نظر خود را وارد کنید");
        commentTextArea.setPrefHeight(80);

        // Create a button to submit comments
        Button submitButton = new Button("ارسال نظر");
        submitButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        submitButton.setOnAction(e -> {
            String comment = commentTextArea.getText();
            Label commentLabel = new Label(comment);
            commentLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            commentGridPane.addRow(commentGridPane.getChildren().size(), commentLabel);
            commentTextArea.clear();
        });

        // Create an HBox for the top right corner
        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10));

        // Create an ImageView for the back button
        Image backButtonImage = new Image("file:///C:\\Users\\ASUS\\Downloads\\star.png");
        ImageView backButtonImageView = new ImageView(backButtonImage);
        backButtonImageView.setFitWidth(30);
        backButtonImageView.setFitHeight(30);

        // Create a button using the ImageView
        Button backButton = new Button();
        backButton.setGraphic(backButtonImageView);
        backButton.setStyle("-fx-background-color: transparent;");

        // Handle the click event of the back button
        backButton.setOnAction(e -> {
            // Perform the necessary actions to navigate back to the previous page
            // You can use methods like primaryStage.close() or load a new FXML file
            System.out.println("Navigating back to the previous page");
        });

        // Add the back button to the top right box
        topRightBox.getChildren().add(backButton);

        // Add the top right box to the top of the BorderPane
        root.setTop(topRightBox);

        // Create a VBox for the comment input section
        VBox commentInputVBox = new VBox(10);
        commentInputVBox.setPadding(new Insets(10));
        commentInputVBox.getChildren().addAll(commentTextArea, submitButton);

        // Add the labels, scroll pane, and comment input VBox to the comment VBox
        commentVBox.getChildren().addAll(new Label("نظرات کاربران"), commentScrollPane, commentInputVBox);

        // Create a HBox for the rating confirmation section
        HBox ratingConfirmationBox = new HBox(10);
        ratingConfirmationBox.setAlignment(Pos.CENTER);
        ratingConfirmationBox.setPadding(new Insets(10));

        Button confirmRatingButton = new Button("امتیاز دادن");
        confirmRatingButton.setStyle("-fx-background-color: #00ff48; -fx-text-fill: white; -fx-font-weight: bold;");
        confirmRatingButton.setOnAction(e -> {
            // Perform the necessary actions when the "تأیید رتبه‌بندی" button is clicked
            System.out.println("Rating Confirmed");
        });
        ratingConfirmationBox.getChildren().addAll(averageRatingLabel, confirmRatingButton);

        // Set the left and center sides of the BorderPane
        root.setLeft(vbox);
        root.setCenter(commentVBox);
        root.setBottom(ratingConfirmationBox);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Display");
        primaryStage.show();
    }

    private void selectRating(int rating) {
        selectedRating = rating;
    }

    private void updateRatingStars() {
        for (int i = 0; i < MAX_RATING; i++) {
            if (i < selectedRating) {
                ratingStars[i].setFill(Color.YELLOW);
            } else {
                ratingStars[i].setFill(Color.TRANSPARENT);
            }
        }
    }

    private Color getColorForRating(double ratingPercentage) {
        // Calculate the RGB values based on the rating percentage
        double red = ratingPercentage;
        double green = 1.0 - ratingPercentage;
        double blue = 0.0;

        // Create the color based on the RGB values
        return Color.color(red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
