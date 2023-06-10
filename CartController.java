package com.example.OnlineShop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

public class CartController {
    @FXML
    private ListView<String> cartListView;

    @FXML
    private Label totalPriceLabel;

    private double totalPrice;

    public void initialize() {
        // Example data for the cart
        ObservableList<String> cartItems = FXCollections.observableArrayList(
                "Product 1 (3)",
                "Product 2 (1)",
                "Product 3 (5)"
        );

        cartListView.setItems(cartItems);

        calculateTotalPrice();
    }

    @FXML
    private void goToDiscounts() {
        // Handle "Go to Discounts" button click
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Discounts");
        alert.setHeaderText(null);
        alert.setContentText("Redirecting to the discounts page...");
        alert.showAndWait();
    }

    @FXML
    private void goForPay() {
        // Handle "Go for Pay" button click
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment");
        alert.setHeaderText(null);
        alert.setContentText("Redirecting to the payment page...");
        alert.showAndWait();
    }

    @FXML
    private void goBack() {
        // Handle "Back" button click
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Back");
        alert.setHeaderText(null);
        alert.setContentText("Going back to the previous page...");
        alert.showAndWait();
    }

    @FXML
    private void increaseProduct() {
        // Handle "Increase Product" button click
        String selectedItem = cartListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Extract the product name and quantity
            String[] parts = selectedItem.split("\\(");
            String productName = parts[0].trim();
            int currentQuantity = Integer.parseInt(parts[1].replaceAll("[^\\d]", ""));
            int newQuantity = currentQuantity + 1;

            // Update the cart item in the list view
            int selectedIndex = cartListView.getSelectionModel().getSelectedIndex();
            ObservableList<String> cartItems = cartListView.getItems();
            cartItems.set(selectedIndex, productName + " (" + newQuantity + ")");

            calculateTotalPrice();
        }
    }

    @FXML
    private void decreaseProduct() {
        // Handle "Decrease Product" button click
        String selectedItem = cartListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Extract the product name and quantity
            String[] parts = selectedItem.split("\\(");
            String productName = parts[0].trim();
            int currentQuantity = Integer.parseInt(parts[1].replaceAll("[^\\d]", ""));
            int newQuantity = currentQuantity - 1;

            // Check if the new quantity is valid
            if (newQuantity >= 0) {
                // Update the cart item in the list view
                int selectedIndex = cartListView.getSelectionModel().getSelectedIndex();
                ObservableList<String> cartItems = cartListView.getItems();
                cartItems.set(selectedIndex, productName + " (" + newQuantity + ")");

                calculateTotalPrice();
            } else {
                // Show an alert indicating that the quantity cannot be negative
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Quantity");
                alert.setHeaderText(null);
                alert.setContentText("The quantity cannot be negative.");
                alert.showAndWait();
            }
        }
    }

    private void calculateTotalPrice() {
        double totalPrice = 0.0;
        ObservableList<String> cartItems = cartListView.getItems();
        for (String item : cartItems) {
            // Extract the product name and quantity
            String[] parts = item.split("\\(");
            String productName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].replaceAll("[^\\d]", ""));

            // Calculate the price for each product (dummy calculation)
            double productPrice = quantity * 10.0;

            totalPrice += productPrice;
        }

        totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
    }
}
