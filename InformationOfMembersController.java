package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class InformationOfMembersController {

    @FXML
    private TextField addressField;

    @FXML
    private Label addressLabel;

    @FXML
    private Button createButton;

    @FXML
    private GridPane grid;

    @FXML
    private Pane infoPane;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField namefield;

    @FXML
    private Label setTitle;

    @FXML
    private AnchorPane warehousePane;

    @FXML
    void back(MouseEvent event)throws IOException {
        Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void createButton(ActionEvent event) {

    }

}
