package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public final class SigningController {

    int role=1;


    @FXML
    private TextField confirmCodeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Label setCodeSend;

    @FXML
    private TextField userNameField;

    @FXML
    private Button customer;

    @FXML
    private Button seller;

    @FXML
    void customerButton(ActionEvent event) {
        customer.setStyle("-fx-background-color: #11DECB");
        seller.setStyle("-fx-background-color: #ffffff");
        role=1;
    }

    int email;
    int code;
    public void send(ActionEvent actionEvent) {

        code=new Random().nextInt(900000)+100000;

        email=EmailSender.send("sinaafzali.org@gmail.com","کد تایید","سلام مشتری گرامی \n کد تایید شما برای ثبت نام در  فروشگاه ما :   "+code);


        if (email==0){
            Methods.notification("کد تایید به ایمیل شما ارسال شد",5);
            confirmCodeField.setEditable(true);
        }else {
            Methods.notification("ایمیل نامعتبر است ",5);
        }

    }

    @FXML
    void loginButton(ActionEvent event) throws IOException, SQLException {

        boolean permission=true;

        firstNameField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        lastNameField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        phoneNumberField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        userNameField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        passwordField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        emailField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        confirmCodeField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");


        if(firstNameField.getText().length()>15||firstNameField.getText().length()<3){
            permission=false;
            firstNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
        if (lastNameField.getText().length()>15||lastNameField.getText().length()<3){
            permission=false;
            lastNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
        if (phoneNumberField.getText().length()!=11 || !Methods.checkFieldText(phoneNumberField.getText())){
            permission=false;
            phoneNumberField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        if ((userNameField.getText().length()>15 || userNameField.getText().length()<5) || Methods.searchUserName(userNameField.getText(),role)){
            permission=false;
            if (Methods.searchUserName(userNameField.getText(),role))Methods.notification("با این نام کاربری قبلا یک حساب کاربری ساخته شده است!",5);
            userNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
        if (passwordField.getText().length()>15 || passwordField.getText().length()<5){
            permission=false;
            passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        int x=emailField.getText().indexOf("@gmail.com");
        if ((emailField.getText().length()==0 || !emailField.getText().contains("@gmail.com") || emailField.getText().length()>x+10)){
            permission=false;
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }




        if (confirmCodeField.getText().length()!=6 || !Methods.checkFieldText(confirmCodeField.getText()) || Integer.parseInt(confirmCodeField.getText())!=code ){
            permission=false;
            confirmCodeField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }


        if (permission){
            Methods.stage.close();
            if (role==1) {
                Person person=new Customer(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), userNameField.getText(), passwordField.getText(), emailField.getText());
                Information.getPersons().add(person);
                Database.addPersons(person);
            }
            if (role==2){
                Person person=new Seller(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), userNameField.getText(), passwordField.getText(), emailField.getText());
                Information.getPersons().add(person);
                Database.addPersons(person);
            }
            Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }

    }

    @FXML
    void sellerButton(ActionEvent event) {
        seller.setStyle("-fx-background-color: #11DECB");
        customer.setStyle("-fx-background-color: #ffffff");
        role=2;
    }


    @FXML
    void back() throws IOException {
        Methods.stage.close();
        Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

}
