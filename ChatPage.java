package com.example.OnlineShop;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ChatPage {

    private ListView<String> chatListView;
    private TextField messageField;
    private Button sendButton;
    private Button backButton;

    public  void start(Stage primaryStage,int back,int user) throws SQLException, IOException {
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
        sendButton.setOnAction(event -> {
            try {
                sendMessage(1,false,null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        messageField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        sendMessage(1,false,null);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        HBox inputContainer = new HBox(10, messageField, sendButton);
        inputContainer.setAlignment(Pos.CENTER);

        VBox chatContainer = new VBox(scrollPane, inputContainer);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        chatContainer.setSpacing(10);
        chatContainer.setStyle("-fx-background-color: white; -fx-border-width: 1px; -fx-border-color: #CCCCCC;");

        root.setCenter(chatContainer);

        backButton = new Button("بستن چت");
        backButton.setStyle("-fx-font-size: 24px; -fx-background-color: #CCCCCC;");
        backButton.setOnAction(event -> {
            try {
                goBack(back);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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
        primaryStage.setFullScreen(true);

        primaryStage.show();



        for (int i=0;i<Information.getMessages().size();i++){

            if (user==1){
                if (Information.getMessages().get(i).getUserSender().equals(Information.getUserName()) &&
                        Information.getMessages().get(i).getRoleSender()==Information.getRole()){
                    sendMessage(1,true,Information.getMessages().get(i).getText());
                }
                int r=0;
                if (Information.getRole()==1)r=3;
                else if (Information.getRole()==2)r=4;
                if (Information.getMessages().get(i).getUserSender().equals(Information.getUserName()) &&
                        Information.getMessages().get(i).getRoleSender()==Information.getRole() &&
                        Information.getMessages().get(i).getRoleSender()==r){
                    sendMessage(2,true,Information.getMessages().get(i).getText());
                }
            }


            if (user==2){

                if (Information.getMessages().get(i).getUserSender().equals(Information.userChat) &&
                        Information.getMessages().get(i).getRoleSender()==Information.roleChat){

                    Information.getMessages().get(i).setRead(0);
                    Database.updateMessage(Information.getMessages().get(i));
                    sendMessage(2,true,Information.getMessages().get(i).getText());
                }
                if (Information.getMessages().get(i).getUserSender().equals(Information.userChat) &&
                        Information.getMessages().get(i).getRoleSender()==3 &&
                          Information.roleChat==1){

                    Information.getMessages().get(i).setRead(0);
                    Database.updateMessage(Information.getMessages().get(i));
                    sendMessage(1,true,Information.getMessages().get(i).getText());
                }


                if (Information.getMessages().get(i).getUserSender().equals(Information.userChat) &&
                        Information.getMessages().get(i).getRoleSender()==4 &&
                        Information.roleChat==2){

                    Information.getMessages().get(i).setRead(0);
                    Database.updateMessage(Information.getMessages().get(i));
                    sendMessage(1,true,Information.getMessages().get(i).getText());
                }
            }

        }






    }

    private void sendMessage(int mode,boolean auto,String message1) throws SQLException, IOException {
        String message = "";
        if (!auto)message = messageField.getText();
        if (auto)message=message1;
        if (!message.isEmpty()) {
            String recipientMessage = "Recipient: " + message;
            String senderMessage = "You: " + message;

            if (mode==1)chatListView.getItems().addAll(recipientMessage);
            if (mode==2)chatListView.getItems().addAll(senderMessage);

            chatListView.scrollTo(chatListView.getItems().size() - 1); // Scroll to the latest message
            messageField.clear();

            if (!auto){
                new server(message).start();
                new client().start();
            }

        }
    }

    private void goBack(int x) throws IOException {
        if (x==1) {
            Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
        if (x==2){
            Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
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
            senderProfileImage = new ImageView(new Image("D:\\Programming\\intellij\\OnlineShop\\src\\main\\resources\\com\\example\\image\\l.png")); // Replace with your sender profile image path
            recipientProfileImage = new ImageView(new Image("D:\\Programming\\intellij\\OnlineShop\\src\\main\\resources\\com\\example\\image\\l.png")); // Replace with your recipient profile image path

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
                        recipientMessageBox.getChildren().addAll(messageButton,recipientProfileImage );
                        recipientMessageBox.setEffect(chatBubbleShadow);
                    }
                }

                setGraphic(messageContainer);
            }
        }
    }
}
