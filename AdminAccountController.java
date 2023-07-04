package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

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
    void checkProductsButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("checkingProducts.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
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
    void seeOthersInformationButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("InformationOfMembers.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void sellInformationButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
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
                if (message.getUserSender().equals(Information.getMessages().get(i).getUserSender()) &&
                        message.getRoleSender() == Information.getMessages().get(i).getRoleSender()) {

                    permission = false;
                    break;
                }
            }
            if (permission)userMessage.add(Information.getMessages().get(i));
        }


        for (int i=0;i<userMessage.size();i++){
            if (userMessage.get(i).getRoleSender()==3 || userMessage.get(i).getRoleSender()==4)userMessage.remove(i);
        }




        for (int i=0;i<userMessage.size();i++){

            String role="";
            if (userMessage.get(i).getRoleSender()==1){
                role="خریدار";
            }else role="فروشنده";

            anchorPane.setPrefHeight((i+1)*60);
            grid.setPrefHeight((i+1)*60);
            Button chatLabel=new Button(String.valueOf(userMessage.get(i).getUserSender()+"( "+role+" )"));
            chatLabel.setFont(Font.font(18));
            chatLabel.setPrefSize(300,60);

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

            HBox hBox=new HBox(chatLabel);

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
