package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class payController {

    @FXML
    private TextField serYear;

    @FXML
    private TextField setActive;

    @FXML
    private TextField setCode;

    @FXML
    private TextField setCodeCard;

    @FXML
    private TextField setCvv2;

    @FXML
    private TextField setEmail;

    @FXML
    private TextField setMoney;

    @FXML
    private TextField setMonth;

    @FXML
    private TextField setUserName;

    @FXML
    private ImageView randomImage;


    @FXML
    void setCancel(ActionEvent event) {

    }

    private String image="src/main/resources/com/example/image/79199.png";

    Methods methods=new Methods();



    public void setInformation(int money,String useName){

        setMoney.setText(String.valueOf(money));

        setUserName.setText(useName);
    }



    private boolean checkFieldText(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)>'9' || s.charAt(i)<'0')return false;
        }
        return true;
    }



    @FXML
    void setPay(ActionEvent event) {


        setMonth.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        serYear.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCodeCard.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCode.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCvv2.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setEmail.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setActive.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");




        boolean permission=true;

        if (setCodeCard.getText().length()!=16 || !checkFieldText(setCodeCard.getText())){
            setCodeCard.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            permission=false;
        }

        if ((setCvv2.getText().length()!=3 && setCvv2.getText().length()!=4) || !checkFieldText(setCvv2.getText()) ){
            setCvv2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            permission=false;
        }

        long time=System.currentTimeMillis();
        long z=0;
        if (serYear.getText().length()>0&&checkFieldText(serYear.getText()))z=(Long.parseLong(serYear.getText())-1348L)*31536000000L;
        if (setMonth.getText().length()>0&&checkFieldText(setMonth.getText()))z+=(Long.parseLong(setMonth.getText())-9L)*2635200000L;

        if ((serYear.getText().length()==0 || setMonth.getText().length()==0) ||
                (!checkFieldText(setMonth.getText()) || !checkFieldText(serYear.getText())) || z<time ||
                  Integer.parseInt(setMonth.getText())>12 || setMonth.getText().length()>2 || serYear.getText().length()!=4){

            serYear.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            setMonth.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }


        if (!setCode.getText().equals(methods.codeImageRand(image))){
            setCode.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        int activeCode= 54321;
        System.out.println(activeCode);
        System.out.println(setActive.getText());
        if (setActive.getText().length()!=5 || Integer.parseInt(setActive.getText())!= activeCode || !checkFieldText(setActive.getText())){
            setActive.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        int x=setEmail.getText().indexOf("@gmail.com");
        if (setEmail.getText().length()==0 || !setEmail.getText().contains("@gmail.com") || setEmail.getText().length()>x+10){
            setEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

    }

    @FXML
    void setPoya(ActionEvent event) {

    }

    @FXML
    void setRefresh(MouseEvent event) throws FileNotFoundException {
        image=methods.imageRand();
        randomImage.setImage(null);
        randomImage.setImage(new Image(new FileInputStream(image)));
    }

    @FXML
    void setSave(ActionEvent event) {

    }

}
