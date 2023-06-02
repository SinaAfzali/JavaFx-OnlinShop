package com.example.demo25;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductScrollPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        VBox contentBox = new VBox();
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));

        // Add product items to the VBox
        for (int i = 1; i <= 20; i++) {
            ProductItem productItem = new ProductItem("Product " + i, "$" + i * 10, "Brand " + i);
            contentBox.getChildren().add(productItem);
        }

        scrollPane.setContent(contentBox);

        Scene scene = new Scene(scrollPane, 400, 400);
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
            Label priceLabel = new Label("Price: " + price);
            Label brandLabel = new Label("Brand: " + brand);

            getChildren().addAll(nameLabel, priceLabel, brandLabel);
            setSpacing(5);
            setPadding(new Insets(5));
            setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        }
    }
}
