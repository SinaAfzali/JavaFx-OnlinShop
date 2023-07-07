package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.IOException;

public final class ForgetController {

    @FXML
    private TextField setCode;

    @FXML
    private TextField setPass;

    @FXML
    private TextField useEmail;

    String code="54321";

    @FXML
    void setConfirm(ActionEvent event) throws IOException {
        if (setCode.getText().equals(code)){
            Methods.stage.close();
            Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
            Methods.stage.setTitle("forget");
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
            Methods.notification("رمز عبور شما با موفقیت تغییر کرد!",5);
        }
    }

    @FXML
    void setReturn(MouseEvent event) throws IOException {
        Methods.stage.close();
        Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
        Methods.stage.setTitle("forget");
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    public void send(ActionEvent actionEvent) {
        useEmail.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        boolean permission=true;
        int x=useEmail.getText().indexOf("@gmail.com");
        if ((useEmail.getText().length()==0 || !useEmail.getText().contains("@gmail.com") || useEmail.getText().length()>x+10)){
            permission=false;
            useEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
        if (permission) {
            setCode.setEditable(true);
        }
    }
}
