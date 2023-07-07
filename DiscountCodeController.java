package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public final class DiscountCodeController {

    @FXML
    private ImageView Logo1;

    @FXML
    private ImageView Logo2;

    @FXML
    private ImageView Logo3;

    @FXML
    private ImageView Logo4;

    @FXML
    private ImageView Logo5;

    @FXML
    private ImageView Logo6;

    @FXML
    private Label discountCodeLabel1;

    @FXML
    private Label discountCodeLabel2;

    @FXML
    private Label discountCodeLabel3;

    @FXML
    private Label discountCodeLabel4;

    @FXML
    private Label discountCodeLabel5;

    @FXML
    private Label discountCodeLabel6;

    @FXML
    private Label header1;

    @FXML
    private Label header2;

    @FXML
    private Label header3;

    @FXML
    private Label header4;

    @FXML
    private Label header5;

    @FXML
    private Label header6;




    Clipboard clipboard=Clipboard.getSystemClipboard();
    ClipboardContent content=new ClipboardContent();


    @FXML
    void Copy1(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel1.getText());
        clipboard.setContent(content);
    }

    @FXML
    void Copy2(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel2.getText());
        clipboard.setContent(content);
    }

    @FXML
    void Copy3(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel3.getText());
        clipboard.setContent(content);
    }

    @FXML
    void Copy4(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel4.getText());
        clipboard.setContent(content);
    }

    @FXML
    void Copy5(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel5.getText());
        clipboard.setContent(content);
    }

    @FXML
    void Copy6(MouseEvent event) {
        Methods.notification("کپی شد!",3);
        content.putString(discountCodeLabel6.getText());
        clipboard.setContent(content);
    }

    @FXML
    void backIcon(MouseEvent event) throws IOException {
        if (Information.backDiscount==1){
            Scene scene = new Scene(Methods.loader("CustomerAccount.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
        if (Information.backDiscount==2){
            Scene scene = new Scene(Methods.loader("cart.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
    }

}
