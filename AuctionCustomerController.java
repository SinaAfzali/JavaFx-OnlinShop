package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.util.ResourceBundle;

public class AuctionCustomerController implements Initializable {


    @FXML
    private Label numberOfProduct;

    @FXML
    private TextField setCustomerPrice;

    @FXML
    private Label setName;

    @FXML
    private ImageView setPhoto;

    @FXML
    private Label setPrice;

    @FXML
    private Label setSeller;

    @FXML
    private Label setTheGreatestPrice;


    @FXML
    void backIcon(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void setPay(ActionEvent event) {

        if (!Information.isLogin()){
            Notifications notificationBuilder = Notifications.create()
                    .title("")
                    .text("ابتدا باید وارد حساب کاربری خود شوید"+"\n"+"(کلیک کنید)                           ")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.TOP_CENTER)
                    .onAction(a -> {
                        Scene scene = null;
                        try {
                            Information.backLogin=4;
                            scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Methods.stage.setScene(scene);
                        Methods.stage.setFullScreen(true);
                        Methods.stage.show();
                    });


            notificationBuilder.showInformation();
        }else if (Information.getRole()!=1){
            Methods.notification("فقط خریداران میتوانند در مزایده شرکت کنند و شما خریدار نیستید",5);
        }else{

            if (Integer.parseInt(setCustomerPrice.getText())<Information.getAuctions().get(Information.INDEXAuction).getBasicPrice()){
                Methods.notification("نمیتوانید مبلفی کمتر از قیمت پایه پیشنهاد دهید",5);
            }else {
                int customer=-1;
              for (int i=0;i<Information.getPersons().size();i++){
                  if (Information.getUserName().equals(Information.getPersons().get(i).getUserName()) &&
                     Information.getPersons().get(i) instanceof Customer){
                      customer=i;
                      break;
                  }
              }

              double m=Information.getAuctions().get(Information.INDEXAuction).getCount()*Double.parseDouble(setCustomerPrice.getText());

              if(Information.getPersons().get(customer).getMoney()<m){
                  Methods.notification("موجودی شما کافی نیست",5);
              }else {
                  int customer2=customer;
                  Notifications notificationBuilder = Notifications.create()
                          .title("")
                          .text("مبلغ "+m+" تومان از حساب شما کسر خواهد شد و تنها در صورتی که \n یک فرد دیگر مبلغ بالاتری پیشنهاد دهد به حسابتان برمیگردد"+"\n"+"                             اگر موافق هستید( کلیک کنید)")
                          .darkStyle()
                          .graphic(null)
                          .hideAfter(Duration.seconds(15))
                          .position(Pos.CENTER)
                          .onAction(a -> {
                              int seller=0;
                              String userSeller="";
                              for (int i=0;i<Information.getProducts().size();i++){
                                  if (Information.getProducts().get(i).getCodeOfProduct().equals(Information.getAuctions().get(Information.INDEXAuction).getCodeOfProduct())){
                                      userSeller=Information.getProducts().get(i).getUserNameSeller();
                                      break;
                                  }
                              }
                              for (int i=0;i< Information.getPersons().size();i++){
                                    if (Information.getPersons().get(i) instanceof Seller &&
                                    Information.getPersons().get(i).getUserName().equals(userSeller)){
                                        seller=i;
                                        break;
                                    }
                              }



                           if (!Information.getAuctions().get(Information.INDEXAuction).getCurrentCustomer().equals(".") &&
                              Information.getAuctions().get(Information.INDEXAuction).getMaxPrice()!=0){
                               int lastCustomer=0;
                               for (int i=0;i<Information.getPersons().size();i++){
                                   if (Information.getPersons().get(i).getUserName().equals(Information.getAuctions().get(Information.INDEXAuction).getCurrentCustomer()) &&
                                       Information.getPersons().get(i) instanceof Customer){
                                       lastCustomer=i;
                                       break;
                                   }
                               }
                               int m2=Information.getAuctions().get(Information.INDEXAuction).getMaxPrice()*Information.getAuctions().get(Information.INDEXAuction).getCount();

                               Information.getPersons().get(lastCustomer).setMoney(Information.getPersons().get(lastCustomer).getMoney()+m2);

                               Information.getPersons().get(seller).setMoney(Information.getPersons().get(seller).getMoney()-m2);

                               Database.updatePerson(Information.getPersons().get(lastCustomer));

                               Database.updatePerson(Information.getPersons().get(seller));

                           }

                              Information.getPersons().get(customer2).setMoney(Information.getPersons().get(customer2).getMoney()-m);
                              Information.getPersons().get(seller).setMoney(Information.getPersons().get(seller).getMoney()+m);
                              Information.getAuctions().get(Information.INDEXAuction).setCurrentCustomer(Information.getUserName());
                              Information.getAuctions().get(Information.INDEXAuction).setMaxPrice(Integer.parseInt(setCustomerPrice.getText()));

                              Database.updatePerson(Information.getPersons().get(customer2));
                              Database.updatePerson(Information.getPersons().get(seller));
                              Database.updateAuction(Information.getAuctions().get(Information.INDEXAuction));

                              start();

                          });


                  notificationBuilder.showInformation();
              }



            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     start();

    }


    public void start(){
        setPrice.setText("قیمت پایه : "+Methods.moneyStandard(Information.getAuctions().get(Information.INDEXAuction).getBasicPrice())+" تومان");

        setTheGreatestPrice.setText("بالا ترین قیمت پیشنهاد شده : "+Methods.moneyStandard(Information.getAuctions().get(Information.INDEXAuction).getMaxPrice())+" تومان");

        try {
            setPhoto.setImage(new Image(new FileInputStream(Information.productAuction.getAddressImage())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        setName.setText("نام کالا : "+Information.productAuction.getName());

        setSeller.setText("فروشنده : "+Information.productAuction.getUserNameSeller());

        numberOfProduct.setText("تعداد کالا : "+Information.getAuctions().get(Information.INDEXAuction).getCount());
    }


    public void goToProductPage(MouseEvent mouseEvent) throws IOException {

        Information.product=Information.productAuction;

        int index=0;

        for (int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getCodeOfProduct().equals(Information.getAuctions().get(Information.INDEXAuction).getCodeOfProduct())){
                index=i;
                break;
            }
        }

        Information.INDEX=index;


        Scene scene = new Scene(Methods.loader("product.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();

    }
}
