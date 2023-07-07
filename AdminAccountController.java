package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminAccountController implements Initializable {

    public AnchorPane anchorPane;
    public GridPane grid;
    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label moneyField;

    @FXML
    private TextField passField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField ruleField;

    @FXML
    private TextField userField;

    @FXML
    void checkProductsButton(ActionEvent event) {

    }

    @FXML
    void goToWareHouseButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("wareHouse.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void returnIcon(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void seeOthersInformationButton(ActionEvent event) {

    }

    @FXML
    void sellInformationButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j && GridPane.getColumnIndex(node)==j);
        }

        ArrayList<message>userMessage=new ArrayList<>();

        for (int i=0;i<Information.getMessages().size();i++){
            boolean permission=true;
            for (com.example.OnlineShop.message message : userMessage) {
                if (Information.getMessages().get(i).getUserSender().equals(message.getUserSender()) &&
                        Information.getMessages().get(i).getRoleSender()==message.getRoleSender()) {

                    permission = false;
                    break;
                }
            }
            if (permission)userMessage.add(Information.getMessages().get(i));
        }


        for (int i=0;i<userMessage.size();i++){
            if (userMessage.get(i).getRoleSender()==3 || userMessage.get(i).getRoleSender()==4) {
                userMessage.remove(userMessage.get(i));
                i--;
            }
        }

       ArrayList<message>userMessage2=new ArrayList<>();

        for (int i=userMessage.size()-1;i>=0;i--){
            userMessage2.add(userMessage.get(i));
        }

        ArrayList<message>userMessage3=new ArrayList<>();

        for (message value : userMessage2) {
            for (com.example.OnlineShop.message message : Information.getMessages()) {
                if (value.getUserSender().equals(message.getUserSender()) &&
                        value.getRoleSender() == message.getRoleSender() && message.getRead() == 1) {
                    userMessage3.add(value);
                    break;
                }
            }
        }

        ArrayList<message>userMessage4=new ArrayList<>();

        for (message value : userMessage2) {
            boolean b = true;
            for (com.example.OnlineShop.message message : userMessage3) {
                if (value.equals(message)) {
                    b = false;
                    break;
                }
            }
            if (b) userMessage4.add(value);
        }


        ArrayList<message>userMessage5=new ArrayList<>();


        userMessage5.addAll(userMessage3);

        userMessage5.addAll(userMessage4);

        userMessage.removeAll(userMessage);

        userMessage.addAll(userMessage5);


        userMessage2.clear();
        userMessage3.clear();
        userMessage4.clear();
        userMessage5.clear();


        for (int i=0;i<userMessage.size();i++){

            String role="";
            if (userMessage.get(i).getRoleSender()==1){
                role="خریدار";
            }else role="فروشنده";

            anchorPane.setPrefHeight((i+1)*70);
            grid.setPrefHeight((i+1)*70);
            Button chatLabel=new Button(String.valueOf(userMessage.get(i).getUserSender()+"( "+role+" )"));
            chatLabel.setFont(Font.font(18));
            chatLabel.setPrefSize(250,70);

            Button iconLabel=new Button();
            try {
                iconLabel.setGraphic(new ImageView(new Image(new FileInputStream("src/main/resources/com/example/image/eye2.png"))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            iconLabel.resize(50,60);
            boolean p=false;
            for (com.example.OnlineShop.message message : Information.getMessages()) {
                if (userMessage.get(i).getUserSender().equals(message.getUserSender()) &&
                        userMessage.get(i).getRoleSender() == message.getRoleSender() && message.getRead() == 1) {
                    p = true;
                    break;
                }
            }

            if (p){
                try {
                    iconLabel.setGraphic(new ImageView(new Image(new FileInputStream("src/main/resources/com/example/image/eye.png"))));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }


            int index=i;
            chatLabel.setOnAction(e ->{
                try {
                    action(userMessage.get(index));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            HBox hBox=new HBox(chatLabel,iconLabel);

            hBox.setPadding(new Insets(5));
            grid.add(hBox,0,i);

        }

    }


    public void action(message message) throws SQLException, IOException {

        Information.userChat=message.getUserSender();

        Information.roleChat=message.getRoleSender();

        new ChatPage().start(Methods.stage,2,2);

    }

}
