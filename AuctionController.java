package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class AuctionController implements Initializable {

    int INDEX=-1;
    int x=1;

    public Label numLabel;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane grid;

    @FXML
    private TextField priceField;

    @FXML
    private Label setCodeSend;

    @FXML
    private TextField timeField;

    @FXML
    void create(ActionEvent event) throws SQLException {
        boolean b=true;
        for (int i=0;i<Information.getAuctions().size();i++){
            if (Information.getAuctions().get(i).getCodeOfProduct().equals(Information.getProducts().get(INDEX).getCodeOfProduct())){
                b=false;
                break;
            }
        }

        if (INDEX!=-1){
            if (priceField.getText().length()==0)Methods.notification("فیلد مبلغ پایه خالیست!!",5);
            else if (timeField.getText().length()==0)Methods.notification("فیلد زمان خالیست!!",5);
            else if (!b){
                Methods.notification("این محصول یک مزایده جاری دارد",5);
            }else {

                Information.getProducts().get(INDEX).setNumberInCart(0);

                auction auction=new auction(Information.getProducts().get(INDEX).getCodeOfProduct()
                ,Integer.parseInt(priceField.getText()),x,Integer.parseInt(timeField.getText()));

                Information.getAuctions().add(auction);
                Database.addAuction(auction);


            }


        }else {
            if (priceField.getText()==null)Methods.notification("فیلد مبلغ پایه خالیست!!",5);
            else if (timeField.getText()==null)Methods.notification("فیلد زمان خالیست!!",5);
            else Methods.notification("لطفا یک محصول را انتخاب کنید",5);
        }

    }

    public void showProduct() throws FileNotFoundException {
        INDEX=-1;
        grid.setHgap(30);
        grid.setVgap(50);
        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j/2 && GridPane.getColumnIndex(node)==j%2);
        }
        int count=0;
        for (int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getUserNameSeller().equals(Information.getUserName()))count++;
        }
        int[]indexs=new int[count];
        count=0;
        for (int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getUserNameSeller().equals(Information.getUserName())){
                indexs[count]=i;
                count++;
            }
        }

        for (int i=0;i<indexs.length;i++) {

            anchorPane.setPrefHeight((i/2+1)*500);
            grid.setPrefHeight((i/2+1)*499.99);
            ImageView imageView = new ImageView(new Image(new FileInputStream(Information.getProducts().get(indexs[i]).getAddressImage())));
            Text text = new Text(Information.getProducts().get(indexs[i]).getName()+"\n"+Information.getProducts().get(indexs[i]).getDescription());
            text.setFont(Font.font("serif",20));
            text.setTextAlignment(TextAlignment.CENTER);
            Button select=new Button("انتخاب محصول");
            select.setFont(Font.font("serif",18));
            select.setStyle("-fx-background-color: #00ff00");
            int index = indexs[i];
            select.setOnAction(e -> {
                try {
                    selectProduct(index);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Label label=new Label(Methods.moneyStandard(Information.getProducts().get(indexs[i]).getPrice())+" تومان");
            label.setPrefSize(250,100);
            label.setAlignment(Pos.CENTER);
            label.setPadding(new Insets(5));
            label.setFont(Font.font("serif",20));
            VBox vbox = new VBox(imageView, text,label,select);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefSize(350,350);
            grid.add(vbox, i%2, i/2);
        }


    }

    private void selectProduct(int index) throws FileNotFoundException {
        INDEX=index;
        grid.setHgap(30);
        grid.setVgap(50);
        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j/2 && GridPane.getColumnIndex(node)==j%2);
        }
        anchorPane.setPrefHeight(370);
        grid.setPrefHeight(370);
        ImageView imageView = new ImageView(new Image(new FileInputStream(Information.getProducts().get(index).getAddressImage())));
        Text text = new Text(Information.getProducts().get(index).getName()+"\n"+Information.getProducts().get(index).getDescription());
        text.setFont(Font.font("serif",20));
        text.setTextAlignment(TextAlignment.CENTER);
        Label label=new Label(Methods.moneyStandard(Information.getProducts().get(index).getPrice())+" تومان");
        label.setPrefSize(250,100);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(5));
        label.setFont(Font.font("serif",20));
        VBox vbox = new VBox(imageView, text,label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(350,350);
        grid.add(vbox, 0, 0);
    }

    public void backIcon(MouseEvent mouseEvent) throws IOException {

        Scene scene = new Scene(Methods.loader("sellerAccount.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            showProduct();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void showProduct2(ActionEvent actionEvent) throws FileNotFoundException {
        showProduct();
    }


    public void minus(ActionEvent actionEvent) {

        if (x>1){
            x-=1;
            numLabel.setText("تعداد : "+x);
        }

    }

    public void plus(ActionEvent actionEvent) {

        if (INDEX!=-1){
            if (x<Information.getProducts().get(INDEX).getQuantity()){
                x+=1;
                numLabel.setText("تعداد : "+x);
            }
        }else {
            Methods.notification("لطفا یک محصول را انتخاب کنید",5);
        }
    }
}
