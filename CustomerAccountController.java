package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class CustomerAccountController implements Initializable {

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
    void setCart(ActionEvent event) {

    }
    @FXML
    void setChangeInformation(ActionEvent event) {

    }

    @FXML
    void setDiscountCode(ActionEvent event) throws IOException {
        Information.backDiscount=1;
        Scene scene = new Scene(Methods.loader("DiscountCode.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }


    @FXML
    void setMoney(ActionEvent event) {

    }

    @FXML
    void setRecords(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("PurchaseRecords.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void setReturn(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=0;i<Information.getPersons().size();i++){
            if (Information.getPersons().get(i) instanceof Customer && Information.getPersons().get(i).getUserName().equals(Information.getUserName())){
                firstNameField.setText(Information.getPersons().get(i).getFirstName());
                lastNameField.setText(Information.getPersons().get(i).getLastName());
                passField.setText(Information.getPersons().get(i).getPassword());
                emailField.setText(Information.getPersons().get(i).getEmail());
                ruleField.setText(Information.getPersons().get(i).getRole());
                phoneField.setText(Information.getPersons().get(i).getPhoneNumber());
                userField.setText(Information.getPersons().get(i).getUserName());
                moneyField.setText(String.valueOf(Methods.moneyStandard(Information.getPersons().get(i).getMoney()))+" تومان");
                break;
            }
        }
    }
}
