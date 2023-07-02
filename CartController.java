package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    public Label totalPrice;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane grid;

    private ArrayList<Label>quantity;

    int money=0;

    @FXML
    void disscountPage(ActionEvent event) throws IOException {
        Information.back=2;
        Scene scene = new Scene(Methods.loader("discountCode.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void payPage(ActionEvent event) throws IOException {
        if (Information.isLogin() && Information.getRole()==1){
            Scene scene = new Scene(Methods.loader("pay2.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
        else {
            Information.back=3;
            Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
            Methods.stage.setScene(scene);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        }
    }

    public void setProduct() throws FileNotFoundException {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j/3 && GridPane.getColumnIndex(node)==j%3);
        }

        quantity=new ArrayList<>();
        int[]indexs;
        int count=0;
        for(int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getNumberInCart()>0)count++;
        }
        indexs=new int[count];
        count=0;
        for(int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getNumberInCart()>0) {
                indexs[count] = i;
                count++;
            }
        }
        for (int i=0;i<indexs.length;i++) {
            anchorPane.setPrefHeight((i/3+1)*500);
            grid.setPrefHeight((i/3+1)*499.99);

            ImageView imageView = new ImageView(new Image(new FileInputStream(Information.getProducts().get(indexs[i]).getAddressImage())));
            Text text = new Text(Information.getProducts().get(indexs[i]).getName()+"\n"+Information.getProducts().get(indexs[i]).getDescription());
            text.setFont(Font.font("serif",20));
            text.setTextAlignment(TextAlignment.CENTER);
            Button minus=new Button("-");
            minus.setFont(Font.font("serif",18));
            minus.setStyle("-fx-background-color: #ff0000");
            int index=indexs[i];
            int index2 = i;
            minus.setOnAction(e -> {
                try {
                    minus(index,index2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            quantity.add(new Label());
            if (Information.getProducts().get(indexs[i]).getNumberInCart()==0)quantity.get(i).setText("افزودن به سبد");
            else quantity.get(i).setText("تعداد : "+Information.getProducts().get(indexs[i]).getNumberInCart());
            quantity.get(i).setFont(Font.font("Arial",20));
            quantity.get(i).setPrefSize(100,35);
            quantity.get(i).setAlignment(Pos.CENTER);
            Button plus=new Button("+");
            plus.setFont(Font.font("serif",18));
            plus.setStyle("-fx-background-color: #00ff00");
            plus.setOnAction(e -> {
                plus(index,index2);
            });
            HBox hBox=new HBox(minus,quantity.get(i),plus);
            hBox.setAlignment(Pos.CENTER);
            Label label=new Label(Methods.moneyStandard(Information.getProducts().get(indexs[i]).getPrice())+" تومان");
            label.setPrefSize(250,100);
            label.setAlignment(Pos.CENTER);
            label.setPadding(new Insets(5));
            label.setFont(Font.font("serif",20));
            VBox vbox = new VBox(imageView, text,label, hBox);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefSize(350,350);
            grid.add(vbox, i%3, i/3);
        }
        setTotalPrice();
    }

    public void minus(int index,int j) throws FileNotFoundException {
        if (Information.getProducts().get(index).getNumberInCart()>0){
            Information.getProducts().get(index).setNumberInCart(Information.getProducts().get(index).getNumberInCart()-1);
            if (Information.getProducts().get(index).getNumberInCart()==0)setProduct();
            else quantity.get(j).setText("تعداد : "+Information.getProducts().get(index).getNumberInCart());
            Database.updateProduct(Information.getProducts().get(index));
        }
        setTotalPrice();
    }

    public void plus(int index,int j){
        if (Information.getProducts().get(index).getNumberInCart() < Information.getProducts().get(index).getQuantity()) {
            Information.getProducts().get(index).setNumberInCart(Information.getProducts().get(index).getNumberInCart() + 1);
            quantity.get(j).setText("تعداد : " + Information.getProducts().get(index).getNumberInCart());
            Database.updateProduct(Information.getProducts().get(index));
        }
        setTotalPrice();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setProduct();
            setTotalPrice();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTotalPrice(){
        money=0;
        for (int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getNumberInCart()>0) {
                money += Information.getProducts().get(i).getNumberInCart()*Information.getProducts().get(i).getPrice();
            }
        }
        totalPrice.setText("     مبلغ قابل پرداخت : "+Methods.moneyStandard(money)+" تومان");
        Information.setTotalPrice(money);
    }

    public void backIcon(MouseEvent mouseEvent) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }
}
