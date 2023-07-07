package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class productController implements Initializable {

    int point=0;

    @FXML
    public Label number;
    @FXML
    public Label name;
    @FXML
    public Label explanation;
    @FXML
    public Label category;
    @FXML

    public Label seller;
    @FXML
    public Label score;
    @FXML
    public ImageView image;
    @FXML
    public Label price;
    @FXML
    private Button point1;

    @FXML
    private Button point2;

    @FXML
    private Button point3;

    @FXML
    private Button point4;

    @FXML
    private Button point5;

    @FXML
    private TextArea theReciveBox;

    @FXML
    private TextArea theSendBox;

    @FXML
    void setBack(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void setDecreas(ActionEvent event) {
        if (Information.getProducts().get(Information.INDEX).getNumberInCart()>0){
            Information.getProducts().get(Information.INDEX).setNumberInCart(Information.getProducts().get(Information.INDEX).getNumberInCart()-1);
            if (Information.getProducts().get(Information.INDEX).getNumberInCart()==0)number.setText("افزودن به سبد");
            else number.setText("تعداد : "+Information.getProducts().get(Information.INDEX).getNumberInCart());
            Database.updateProduct(Information.getProducts().get(Information.INDEX));
        }
    }

    @FXML
    void setIncreas(ActionEvent event) {
        if (Information.getProducts().get(Information.INDEX).getNumberInCart() < Information.getProducts().get(Information.INDEX).getQuantity()) {
            Information.getProducts().get(Information.INDEX).setNumberInCart(Information.getProducts().get(Information.INDEX).getNumberInCart() + 1);
            number.setText("تعداد : " + Information.getProducts().get(Information.INDEX).getNumberInCart());
            Database.updateProduct(Information.getProducts().get(Information.INDEX));
        }
    }

    @FXML
    void setPoint(ActionEvent event) throws SQLException {

        if (!Information.isLogin()){
            notification();
        }else{

            boolean p=false;
            for (int i=0;i<Information.getTransactions().size();i++){
                if (Information.getUserName().equals(Information.getTransactions().get(i).getCustomer()) &&
                  Information.product.getCodeOfProduct().equals(Information.getTransactions().get(i).getCodeOfProduct()))p=true;
            }
            boolean b=true;
            for (int i=0;i<Information.getPoints().size();i++){
                if(Information.getPoints().get(i).getUserName().equals(Information.getUserName()) &&
                  Information.getPoints().get(i).getCodeOfProduct().equals(Information.product.getCodeOfProduct())){
                    b=false;
                }
            }

            if (Information.getRole()!=1){
                Methods.notification("شما خریدار نیستید. فقط خریداران میتوانند امتیاز بدهند",5);
            }else if (!p){
                Methods.notification("شما این محصول را خریداری نکرده اید. پس نمیتوانید به آن امتیاز بدهید",5);
            }else if (!b){
                Methods.notification("شما قبلا به این محصول امتیاز داده اید",5);
            }else {
                Methods.notification("امتیاز شما با موفقیت ثبت شد",5);

                point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
                point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
                point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
                point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
                point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");

                double x=Information.product.getCountScore()*Information.product.getScore();
                x+=point;
                x/=(Information.product.getCountScore()+1);

                Information.getProducts().get(Information.INDEX).setScore(x);
                Database.updateProduct(Information.getProducts().get(Information.INDEX));

                score.setText("امتیاز محصول : "+Information.getProducts().get(Information.INDEX).getScore());

                com.example.OnlineShop.point point10 =new point(Information.getUserName(),Information.product.getCodeOfProduct());
                Information.getPoints().add(point10);
                Database.addPoint(point10);

            }
        }

    }

    String  text = "";
    @FXML
    void setSend(ActionEvent event) throws IOException, SQLException {



        if (Information.isLogin() && Information.getRole()==1){
            boolean bought=false;
            for (int i=0;i<Information.getTransactions().size();i++){
                if (Information.getTransactions().get(i).getCustomer().equals(Information.getUserName()) &&
                     Information.getTransactions().get(i).getCodeOfProduct().equals(Information.product.getCodeOfProduct())){
                    bought=true;
                    break;
                }
            }
            String t="";
            if (!bought)t="نخریده است";
            else t= "خریده است";
            text+=Information.getUserName()+"( "+t+" )"+
                    " : "+"\n"+theSendBox.getText()+"\n\n\n";

            theReciveBox.setText(text);

            int b=1;

            if (bought)b=0;

            Comment comment=new Comment(Information.getUserName(),theSendBox.getText(),Information.product.getCodeOfProduct(),b);
            Database.addComment(comment);

            theSendBox.setText("");

        }else if (!Information.isLogin()){
           notification();
        }else {
            Methods.notification("شما فروشنده هستید بنابراین نمیتوانید نظر دهید",4);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String s = "";

        if (Information.product.getNumberInCart()>0)s = "تعداد : "+String.valueOf(Information.product.getNumberInCart());
        else s = "افزودن به سبد";

        number.setText(s);

        name.setText("نام  : "+Information.product.getName());
        explanation.setText("توضیحات  : "+Information.product.getDescription());
        category.setText("دسته بندی : "+Information.product.getCategory());
        seller.setText("فروشنده : "+Information.product.getUserNameSeller());
        score.setText("امتیاز محصول : "+Information.product.getScore());

        number.setPrefSize(150,30);
        number.setAlignment(Pos.CENTER);
        try {
            image.setImage(new Image(new FileInputStream(Information.product.getAddressImage())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        price.setText("قیمت : "+Methods.moneyStandard(Information.product.getPrice())+" تومان");


        theReciveBox.setFont(new Font("serif",20));
        theSendBox.setFont(new Font("serif",16));



        for (int i = 0; i < Information.getComments().size(); i++) {
            if (Information.getComments().get(i).getCodeOfProduct().equals(Information.product.getCodeOfProduct())) {

                String t="";
                if (Information.getComments().get(i).getBought()==1)t="نخریده است";
                else t= "خریده است";
                text+=Information.getComments().get(i).getUserName()+"( "+t+" )"+
                        " : "+"\n"+Information.getComments().get(i).getTextComment()+"\n\n\n";

            }
        }

        theReciveBox.setText(text);






        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
    }



    public void notification(){
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
                        Information.backLogin=3;
                        scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Methods.stage.setScene(scene);
                    Methods.stage.setFullScreen(true);
                    Methods.stage.show();
                });


        notificationBuilder.showInformation();
    }





    public void point1(ActionEvent actionEvent) {
        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point=1;
    }

    public void point2(ActionEvent actionEvent) {
        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point=2;
    }

    public void point3(ActionEvent actionEvent) {
        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point=3;
    }

    public void point4(ActionEvent actionEvent) {
        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #ffffff");
        point=4;
    }

    public void point5(ActionEvent actionEvent) {
        point1.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point2.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point3.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point4.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point5.setStyle("-fx-background-radius: 25px25px;-fx-background-color: #e9ff1d");
        point=5;
    }
}
