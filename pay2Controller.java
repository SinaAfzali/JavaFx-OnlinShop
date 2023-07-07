package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pay2Controller implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private TextField amountDiscount;

    @FXML
    private TextField codeField;

    @FXML
    private Text lastDiscount;

    @FXML
    private Text nextDiscount;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Label titleText;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void completePay(ActionEvent event) throws IOException {
        Information.address=addressField.getText();
        Information.phoneNumber=phoneNumberField.getText();

        Scene scene = new Scene(Methods.loader("pay.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    public void signingDiscount(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastDiscount.setText("قیمت پیش از اعمال تخفیف : "+Methods.moneyStandard(Information.getTotalPrice()));
        nextDiscount.setText("قیمت پس از اعمال تخفیف : "+Methods.moneyStandard(Information.getTotalPrice()));
        titleText.setText("ثبت خرید برای کاربر با نام کاربری   "+Information.getUserName());
    }
}
