package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public final class payController implements Initializable {

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
    void setCancel(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("pay2.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }


    private String image="src/main/resources/com/example/image/79199.png";




    public void setInformation(int money,String useName){

        setMoney.setText(String.valueOf(money));

        setUserName.setText(useName);
    }







    @FXML
    void setPay(ActionEvent event) throws SQLException, IOException {


        setMonth.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        serYear.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCodeCard.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCode.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setCvv2.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setEmail.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        setActive.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");




        boolean permission=true;

        if (setCodeCard.getText().length()!=16 || !Methods.checkFieldText(setCodeCard.getText())){
            setCodeCard.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            permission=false;
        }

        if ((setCvv2.getText().length()!=3 && setCvv2.getText().length()!=4) || !Methods.checkFieldText(setCvv2.getText()) ){
            setCvv2.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            permission=false;
        }

        long time=System.currentTimeMillis();
        long z=0;
        if (serYear.getText().length()>0&&Methods.checkFieldText(serYear.getText()))z=(Long.parseLong(serYear.getText())-1348L)*31536000000L;
        if (setMonth.getText().length()>0&&Methods.checkFieldText(setMonth.getText()))z+=(Long.parseLong(setMonth.getText())-9L)*2635200000L;

        if ((serYear.getText().length()==0 || setMonth.getText().length()==0) ||
                (!Methods.checkFieldText(setMonth.getText()) || !Methods.checkFieldText(serYear.getText())) || z<time ||
                  Integer.parseInt(setMonth.getText())>12 || setMonth.getText().length()>2 || serYear.getText().length()!=4){
            permission=false;
            serYear.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            setMonth.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }


        if (!setCode.getText().equals(Methods.codeImageRand(image))){
            permission=false;
            setCode.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        int activeCode= 54321;

        System.out.println(setActive.getText());
        if (setActive.getText().length()!=5 || Integer.parseInt(setActive.getText())!= activeCode || !Methods.checkFieldText(setActive.getText())){
            permission=false;
            setActive.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }

        int x=setEmail.getText().indexOf("@gmail.com");
        if (setEmail.getText().length()==0 || !setEmail.getText().contains("@gmail.com") || setEmail.getText().length()>x+10){
            permission=false;
            setEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
        int d=-1;
        for (int i=0;i<Information.getPersons().size();i++){
            if (Information.getPersons().get(i) instanceof Customer && Information.getPersons().get(i).getUserName().equals(Information.getUserName())){
                d=i;
                break;
            }
        }
        if (Information.getTotalPrice()>Information.getPersons().get(d).getMoney()){
            permission=false;
            Methods.notification("موجودی حساب شما کافی نمی باشد",3);
        }


        if (permission){

            String code=Methods.codeRandomString();
          for (int i=0;i<Information.getProducts().size();i++){
              if (Information.getProducts().get(i).getNumberInCart()>0){
                  transaction transaction=new transaction(Information.getUserName(),Information.getProducts().get(i).getUserNameSeller(),
                          Information.getProducts().get(i).getCodeOfProduct(),code,Information.address, LocalDate.now().toString(),
                          Information.getProducts().get(i).getNumberInCart(), Information.phoneNumber,(Information.getProducts().get(i).getPrice()*Information.getProducts().get(i).getNumberInCart()));

                  for (int j=0;j<Information.getPersons().size();j++){
                      if (Information.getPersons().get(j) instanceof Seller && Information.getProducts().get(i).getUserNameSeller().equals(Information.getPersons().get(j).getUserName())){
                          double money=Information.getProducts().get(i).getQuantity()*Information.getProducts().get(i).getNumberInCart()*Information.getProducts().get(i).getPrice();
                          Information.getPersons().get(j).setMoney(Information.getPersons().get(j).getMoney()+money);
                      }
                  }



//                  for (int j=0;j<Information.getComments().size();j++){
//
//                      if (Information.getProducts().get(i).getCodeOfProduct().equals(Information.getComments().get(j).getCodeOfProduct()) &&
//                         Information.getUserName().equals(Information.getComments().get(j).getUserName())){
//
//                          Information.getComments().get(j).setBought(0);
//                          Database.updateComment(Information.getComments().get(j));
//                      }
//                  }



                  Information.getTransactions().add(transaction);
                  Database.addTransaction(transaction);

                  Information.getProducts().get(i).setQuantity(Information.getProducts().get(i).getQuantity()-Information.getProducts().get(i).getNumberInCart());
                  Information.getProducts().get(i).setNumberInCart(0);
                  Database.updateProduct(Information.getProducts().get(i));
              }
          }
          Methods.notification("سفارش شما با موفقیت ثبت شد",5);

          Information.getPersons().get(d).setMoney(( Information.getPersons().get(d)).getMoney()-Information.getTotalPrice());


            Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }



    }

    @FXML
    void setPoya(ActionEvent event) {

    }

    @FXML
    void setRefresh(MouseEvent event) throws FileNotFoundException {
        image=Methods.imageRand();
        randomImage.setImage(null);
        randomImage.setImage(new Image(new FileInputStream(image)));
    }

    @FXML
    void setSave(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMoney.setText("مبلغ : "+Methods.moneyStandard(Information.getTotalPrice()));
        setUserName.setText("برای نام کاربری :  "+Information.getUserName());

        image=Methods.imageRand();
        randomImage.setImage(null);
        try {
            randomImage.setImage(new Image(new FileInputStream(image)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
