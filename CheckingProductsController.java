package com.example.OnlineShop;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CheckingProductsController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane grid;

    @FXML
    void backIcon(MouseEvent event)throws IOException {
        Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

}
