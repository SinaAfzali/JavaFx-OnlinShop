package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AdminAccountController {

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
    void returnIcon(MouseEvent event) {

    }

    @FXML
    void seeOthersInformationButton(ActionEvent event) {

    }

    @FXML
    void sellInformationButton(ActionEvent event) {

    }

}
