package com.example.demo25;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductScrollPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox contentBox = new VBox();
        contentBox.setSpacing(20);
        contentBox.setPadding(new Insets(20));

        // Add product items to the VBox
        for (int i = 1; i <= 20; i++) {
            ProductItem productItem = new ProductItem("Product " + i, "$" + i * 10, "Brand " + i);
            contentBox.getChildren().add(productItem);
        }

        scrollPane.setContent(contentBox);

        StackPane root = new StackPane(scrollPane);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f2f2f2;");

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Scroll Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Custom product item class
    private static class ProductItem extends VBox {
        public ProductItem(String name, String price, String brand) {
            Label nameLabel = new Label(name);
            nameLabel.setFont(Font.font("Arial", 16));
            nameLabel.setTextFill(Color.DARKBLUE);

            Label priceLabel = new Label("Price: " + price);
            priceLabel.setFont(Font.font("Arial", 14));

            Label brandLabel = new Label("Brand: " + brand);
            brandLabel.setFont(Font.font("Arial", 14));

            getChildren().addAll(nameLabel, priceLabel, brandLabel);
            setSpacing(5);
            setPadding(new Insets(10));
            setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1px; -fx-border-radius: 5px;");
        }
    }
}
