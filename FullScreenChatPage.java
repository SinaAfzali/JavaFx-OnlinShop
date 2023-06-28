package com.example.chat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FullScreenChatPage extends Application {

    private ListView<String> chatListView;
    private TextField messageField;
    private Button sendButton;
    private Button backButton;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        chatListView = new ListView<>();
        chatListView.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight());
        chatListView.setCellFactory(param -> new ChatListCell());
        chatListView.setStyle("-fx-background-color: #F5F5F5;-fx-prompt-text-fill: #999999; -fx-font-size: 24px;");
        chatListView.setPadding(new Insets(0,250,50,250));


        ScrollPane scrollPane = new ScrollPane(chatListView);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        messageField = new TextField();
        messageField.setPromptText("Enter your message...");
        messageField.setStyle("-fx-prompt-text-fill: #999999; -fx-font-size: 24px;");

        sendButton = new Button("ارسال");
        sendButton.setStyle("-fx-font-size: 24px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        sendButton.setOnAction(event -> sendMessage());

        HBox inputContainer = new HBox(10, messageField, sendButton);
        inputContainer.setAlignment(Pos.CENTER);

        VBox chatContainer = new VBox(scrollPane, inputContainer);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        chatContainer.setSpacing(10);
        chatContainer.setStyle("-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: #CCCCCC;");

        root.setCenter(chatContainer);

        backButton = new Button("بستن چت");
        backButton.setStyle("-fx-font-size: 24px; -fx-background-color: #CCCCCC;");
        backButton.setOnAction(event -> goBack(primaryStage));

        HBox topContainer = new HBox(backButton);
        topContainer.setAlignment(Pos.CENTER_LEFT);
        topContainer.setPadding(new Insets(10));
        topContainer.setStyle("-fx-background-color: #F5F5F5;");

        root.setTop(topContainer);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Full-Screen Chat Page");
        primaryStage.setScene(scene);

        // Maximize the window
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            String senderMessage = "You: " + message;
            String recipientMessage = "Recipient: " + message;

            chatListView.getItems().addAll(senderMessage, recipientMessage);
            chatListView.scrollTo(chatListView.getItems().size() - 1); // Scroll to the latest message
            messageField.clear();
        }
    }

    private void goBack(Stage stage) {
        // Add logic to go back to the previous page here
        stage.close(); // Close the chat window
    }

    private class ChatListCell extends ListCell<String> {
        private final VBox messageContainer;
        private final HBox senderMessageBox;
        private final HBox recipientMessageBox;
        private final ImageView senderProfileImage;
        private final ImageView recipientProfileImage;

        public ChatListCell() {
            messageContainer = new VBox();
            senderMessageBox = new HBox();
            recipientMessageBox = new HBox();
            senderProfileImage = new ImageView(new Image("C:\\Users\\ASUS\\Documents\\ts\\image\\dice11.jpg")); // Replace with your sender profile image path
            recipientProfileImage = new ImageView(new Image("C:\\Users\\ASUS\\Documents\\ts\\image\\dice22.jpg")); // Replace with your recipient profile image path

            senderMessageBox.setAlignment(Pos.CENTER_LEFT);
            recipientMessageBox.setAlignment(Pos.CENTER_RIGHT);

            senderMessageBox.setFillHeight(true);
            recipientMessageBox.setFillHeight(true);

            senderMessageBox.setSpacing(10);
            recipientMessageBox.setSpacing(10);

            senderMessageBox.setPadding(new Insets(0));
            recipientMessageBox.setPadding(new Insets(0));

            senderMessageBox.setStyle("-fx-background-color: #794caf; -fx-background-radius: 10;");
            recipientMessageBox.setStyle("-fx-background-color: #54c4c0; -fx-background-radius: 10;");

            messageContainer.getChildren().addAll(senderMessageBox, recipientMessageBox);
            setGraphic(messageContainer);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                senderMessageBox.getChildren().clear();
                recipientMessageBox.getChildren().clear();

                String[] parts = item.split(":");
                if (parts.length == 2) {
                    String sender = parts[0];
                    String message = parts[1];

                    Button messageButton = new Button(message);
                    messageButton.setWrapText(true);
                    messageButton.setTextFill(Color.WHITE);
                    messageButton.setStyle("-fx-background-color: transparent; -fx-padding: 5px;");

                    DropShadow chatBubbleShadow = new DropShadow();
                    chatBubbleShadow.setColor(Color.GRAY);
                    chatBubbleShadow.setRadius(3);
                    chatBubbleShadow.setSpread(0.6);
                    chatBubbleShadow.setOffsetX(1);
                    chatBubbleShadow.setOffsetY(1);

                    if (sender.equalsIgnoreCase("You")) {
                        senderMessageBox.getChildren().addAll(senderProfileImage, messageButton);
                        senderMessageBox.setEffect(chatBubbleShadow);
                    } else if (sender.equalsIgnoreCase("Recipient")) {
                        recipientMessageBox.getChildren().addAll(recipientProfileImage, messageButton);
                        recipientMessageBox.setEffect(chatBubbleShadow);
                    }
                }

                setGraphic(messageContainer);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
