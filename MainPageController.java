package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public final class MainPageController implements Initializable {
    public TextField searchField;
    @FXML
    public AnchorPane anchorPane2;
    @FXML
    public GridPane grid2;
    @FXML
    private TextField minHide;
    @FXML
    private TextField maxHide;
    @FXML
    private Button applyFilter;
    @FXML
    private Pane pricePane;
    private ArrayList<Label>quantity=new ArrayList<>();

    private int category = 0;
    private int mode=0;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button brand;

    @FXML
    private Button cheapest;

    @FXML
    private Button expensive;

    @FXML
    private GridPane grid;

    @FXML
    private Button newest;

    @FXML
    private Button price;

    @FXML
    private Button score;


    @FXML
    void beverages(ActionEvent event) throws FileNotFoundException {
        category=1;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void brand(ActionEvent event) {

    }

    @FXML
    void breakFast(ActionEvent event) throws FileNotFoundException {
        category=2;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }


    double min;
    double max;

    public void setProduct(int type) throws FileNotFoundException {
        grid.setHgap(30);
        grid.setVgap(50);
        grid.setPadding(new Insets(50));
        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j/4 && GridPane.getColumnIndex(node)==j%4);
        }

        quantity=new ArrayList<>();
        int[]indexs;
        switch (type){
            case 0:indexs = Methods.sortByNewest(category);break;
            case 1:indexs = Methods.sortBy(Information.getProducts(),type,category);break;
            case 2:indexs = Methods.sortBy(Information.getProducts(),type,category);break;
            case 3:indexs = Methods.sortBy(Information.getProducts(),type,category);break;
            case 4:
            case 5:
                indexs = Methods.sortByPrice(Information.getProducts(),min,max,category);break;
            default:indexs=new int[10];
        }
        for (int i=0;i<indexs.length;i++) {
            anchorPane.setPrefHeight((i/4+1)*500);
            grid.setPrefHeight((i/4+1)*499.99);
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
                minus(index,index2);
            });
            quantity.add(new Label());
            if (Information.getProducts().get(indexs[i]).getNumberInCart()==0)quantity.get(i).setText("افزودن به سبد");
            else quantity.get(i).setText("تعداد : "+Information.getProducts().get(indexs[i]).getNumberInCart());
            quantity.get(i).setFont(Font.font("Arial",20));
            quantity.get(i).setAlignment(Pos.CENTER);
            Button plus=new Button("+");
            plus.setFont(Font.font("serif",18));
            plus.setStyle("-fx-background-color: #00ff00");

            plus.setOnAction(e -> {
                plus(index,index2);
            });

            imageView.setOnMouseClicked(e ->{
                try {
                    goToProductPage(Information.getProducts().get(index),index);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            HBox hBox = null;
            if (Information.getProducts().get(i).getNumberInCart()==0) {
                quantity.get(i).setPrefSize(100, 35);
                hBox=new HBox(minus,quantity.get(i),plus);
            }
            else {
                quantity.get(i).setPrefSize(100, 60);
                hBox=new HBox(quantity.get(i));
            }

            hBox.setAlignment(Pos.CENTER);
            Label label=new Label(Methods.moneyStandard(Information.getProducts().get(indexs[i]).getPrice())+" تومان");
            label.setPrefSize(250,100);
            label.setAlignment(Pos.CENTER);
            label.setPadding(new Insets(5));
            label.setFont(Font.font("serif",20));
            VBox vbox = new VBox(imageView, text,label, hBox);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefSize(350,350);
            grid.add(vbox, i%4, i/4);
        }
    }


    @FXML
    void cheapest(ActionEvent event) throws FileNotFoundException {
        mode=1;
        setProduct(1);
        cheapest.setStyle("-fx-background-color: #019eff");
        expensive.setStyle("-fx-background-color: #b6bbff");
        newest.setStyle("-fx-background-color: #b6bbff");
        score.setStyle("-fx-background-color: #b6bbff");
        price.setStyle("-fx-background-color: #b6bbff");
        brand.setStyle("-fx-background-color: #b6bbff");
    }

    @FXML
    void dairy(ActionEvent event) throws FileNotFoundException {
        category=4;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void expensive(ActionEvent event) throws FileNotFoundException {
        mode=2;
        setProduct(2);
        expensive.setStyle("-fx-background-color: #019eff");
        cheapest.setStyle("-fx-background-color: #b6bbff");
        newest.setStyle("-fx-background-color: #b6bbff");
        score.setStyle("-fx-background-color: #b6bbff");
        price.setStyle("-fx-background-color: #b6bbff");
        brand.setStyle("-fx-background-color: #b6bbff");
    }

    @FXML
    void fruits(ActionEvent event) throws FileNotFoundException {
        category=5;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void grocery(ActionEvent event) throws FileNotFoundException {
        category=6;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void newest(ActionEvent event) throws FileNotFoundException {
        mode=0;
        setProduct(0);
        newest.setStyle("-fx-background-color: #019eff");
        expensive.setStyle("-fx-background-color: #b6bbff");
        cheapest.setStyle("-fx-background-color: #b6bbff");
        score.setStyle("-fx-background-color: #b6bbff");
        price.setStyle("-fx-background-color: #b6bbff");
        brand.setStyle("-fx-background-color: #b6bbff");
        }

    @FXML
    void nuts(ActionEvent event) throws FileNotFoundException {
        category=7;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    int v=2;
    @FXML
    void price(ActionEvent event) {
        score.setStyle("-fx-background-color: #b6bbff");
        expensive.setStyle("-fx-background-color: #b6bbff");
        cheapest.setStyle("-fx-background-color: #b6bbff");
        newest.setStyle("-fx-background-color: #b6bbff");
        price.setStyle("-fx-background-color: #019eff");
        brand.setStyle("-fx-background-color: #b6bbff");

        mode=5;
        if (v==2) {
            pricePane.setStyle("-fx-background-color: #9617bd");
            pricePane.setVisible(true);
            minHide.setVisible(true);
            maxHide.setVisible(true);
            applyFilter.setVisible(true);
            v=1;
        }
        else if (v==1) {
            pricePane.setStyle("-fx-background-color: null");
            pricePane.setVisible(false);
            minHide.setVisible(false);
            maxHide.setVisible(false);
            applyFilter.setVisible(false);
            v=2;
        }
    }

    @FXML
    void protein(ActionEvent event) throws FileNotFoundException {
        category=8;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void readyFood(ActionEvent event) throws FileNotFoundException {
        category=3;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void score(ActionEvent event) throws FileNotFoundException {
        mode=3;
      setProduct(3);
        score.setStyle("-fx-background-color: #019eff");
        expensive.setStyle("-fx-background-color: #b6bbff");
        cheapest.setStyle("-fx-background-color: #b6bbff");
        newest.setStyle("-fx-background-color: #b6bbff");
        price.setStyle("-fx-background-color: #b6bbff");
        brand.setStyle("-fx-background-color: #b6bbff");
    }

    boolean search=false;
    @FXML
    void search(MouseEvent event) throws FileNotFoundException {

        searchField.setAlignment(Pos.CENTER);

        if (!search){
            searchField.setVisible(true);
            search=true;

        }else if (search){
            search=false;
            searchField.setVisible(false);
            grid.setHgap(30);
            grid.setVgap(50);
            grid.setPadding(new Insets(50));
            for (int i=0;i<Information.getProducts().size();i++){
                int j=i;
                grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j/4 && GridPane.getColumnIndex(node)==j%4);
            }
            quantity=new ArrayList<>();
            int[]indexs= Methods.searchName(searchField.getText());
            for (int i=0;i<indexs.length;i++) {
                anchorPane.setPrefHeight((i/4+1)*500);
                grid.setPrefHeight((i/4+1)*499);
                ImageView imageView = new ImageView(new Image(new FileInputStream(Information.getProducts().get(indexs[i]).getAddressImage())));
                Text text = new Text(Information.getProducts().get(indexs[i]).getName()+"\n"+Information.getProducts().get(indexs[i]).getDescription());
                text.setFont(Font.font("serif",20));
                text.setTextAlignment(TextAlignment.CENTER);
                Button minus=new Button("-");
                minus.setFont(Font.font("serif",18));
                minus.setStyle("-fx-background-color: #ff0000");
                int index = indexs[i];
                int index2=i;
                minus.setOnAction(e -> {
                    minus(index,index2);
                });
                quantity.add(new Label());

                imageView.setOnMouseClicked(e ->{
                    try {
                        goToProductPage(Information.getProducts().get(index),index);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

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
                grid.add(vbox, i%4, i/4);
            }

            searchField.setText("");

        }

    }

    @FXML
    void snacks(ActionEvent event) throws FileNotFoundException {
        category=9;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    @FXML
    void spices(ActionEvent event) throws FileNotFoundException {
        category=10;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    public void plus(int index,int j) {
        boolean p=true;
        for (int i=0;i<Information.getAuctions().size();i++){
            if (Information.getAuctions().get(i).getCodeOfProduct().equals(Information.getProducts().get(index).getCodeOfProduct())){
                p=false;
                break;
            }
        }


        if (p){
            if (Information.getProducts().get(index).getNumberInCart() < Information.getProducts().get(index).getQuantity()) {
                Information.getProducts().get(index).setNumberInCart(Information.getProducts().get(index).getNumberInCart() + 1);
                quantity.get(j).setText("تعداد : " + Information.getProducts().get(index).getNumberInCart());
                Database.updateProduct(Information.getProducts().get(index));
            }
        }else Methods.notification("این کالا در مزایده است . به بخش مزایده ها مراجعه کنید",5);


    }
    public void minus(int index,int j){
        if (Information.getProducts().get(index).getNumberInCart()>0){
            Information.getProducts().get(index).setNumberInCart(Information.getProducts().get(index).getNumberInCart()-1);
            if (Information.getProducts().get(index).getNumberInCart()==0) {
                quantity.get(j).setText("افزودن به سبد");
            }
            else quantity.get(j).setText("تعداد : "+Information.getProducts().get(index).getNumberInCart());
            Database.updateProduct(Information.getProducts().get(index));
        }
    }


    public void goToProductPage(Product product,int index) throws IOException {

        Information.product=product;

        Information.INDEX=index;

        Scene scene = new Scene(Methods.loader("product.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }




    @FXML
    public void applyFilter(ActionEvent actionEvent) throws FileNotFoundException {
        setProduct(5);
        pricePane.setStyle("-fx-background-color: null");
        pricePane.setVisible(false);
        minHide.setVisible(false);
        maxHide.setVisible(false);
        applyFilter.setVisible(false);

        min = Double.parseDouble(minHide.getText());
        max = Double.parseDouble(maxHide.getText());

        minHide.setText("");
        maxHide.setText("");
    }

    @FXML
    public void login(MouseEvent mouseEvent) throws IOException {
       if (!Information.isLogin()) {
           Information.backLogin=1;
           Scene scene = new Scene(Methods.loader("Login.fxml").load(), 500, 600);
           Methods.stage.setScene(scene);
           Methods.stage.setFullScreen(true);
           Methods.stage.show();
       }else {
           if (Information.getRole()==1){
               Scene scene = new Scene(Methods.loader("CustomerAccount.fxml").load(), 500, 600);
               Methods.stage.setScene(scene);
               Methods.stage.setFullScreen(true);
               Methods.stage.show();
           }else if (Information.getRole()==2){
               Scene scene = new Scene(Methods.loader("sellerAccount.fxml").load(), 500, 600);
               Methods.stage.setScene(scene);
               Methods.stage.setFullScreen(true);
               Methods.stage.show();
           }else {
               Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
               Methods.stage.setScene(scene);
               Methods.stage.setFullScreen(true);
               Methods.stage.show();
           }
       }
    }

    public void shop(MouseEvent mouseEvent) throws IOException {
        Scene scene = new Scene(Methods.loader("cart.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setProduct(0);
            setAuctions();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setAuctions() throws FileNotFoundException, SQLException {


        grid2.setHgap(30);
        grid2.setVgap(50);
        grid2.setPadding(new Insets(50));
        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid2.getChildren().removeIf(node -> GridPane.getRowIndex(node)==0 && GridPane.getColumnIndex(node)==j);
        }


        int[]indexs=new int[Information.getAuctions().size()];


        for (int i=0;i<Information.getAuctions().size();i++){
            int x=Information.getAuctions().get(i).getTime()*36;
            int y= (int) (System.currentTimeMillis()/100000);
            int z=y-x;
            if (Information.getAuctions().get(i).getTimeStart()<z){
                Database.deleteAuction(Information.getAuctions().get(i));
                Information.getAuctions().remove(Information.getAuctions().get(i));
                i--;
            }
        }



        for (int i=0;i<Information.getAuctions().size();i++){
            for (int j=0;j<Information.getProducts().size();j++){
                if (Information.getAuctions().get(i).getCodeOfProduct().equals(Information.getProducts().get(j).getCodeOfProduct())){
                    indexs[i]=j;
                    break;
                }
            }
        }



        for (int i=0;i<indexs.length;i++) {

            anchorPane2.setPrefWidth((i+1)*500);
            grid2.setPrefWidth((i+1)*499.99);
            ImageView imageView = new ImageView(new Image(new FileInputStream(Information.getProducts().get(indexs[i]).getAddressImage())));
            Text text = new Text(Information.getProducts().get(indexs[i]).getName()+"\n"+Information.getProducts().get(indexs[i]).getDescription());
            text.setFont(Font.font("serif",20));
            text.setTextAlignment(TextAlignment.CENTER);
            int index=indexs[i];
            int index1=i;
            imageView.setOnMouseClicked(e ->{
                try {
                    goToAuctionPage(Information.getProducts().get(index),index1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            int x=Information.getAuctions().get(i).getTime()*36;
            int y= (int) ((System.currentTimeMillis()/100000)-Information.getAuctions().get(i).getTimeStart());
            x-=y;
            x/=36;
            String s="";
            if (x==0)s="کمتر از 1 ساعت";
            else s=x+" ساعت";

            Label label=new Label("بالاترین قیمت : "+Methods.moneyStandard(Information.getAuctions().get(i).getMaxPrice())+" تومان");
            Label label1=new Label("زمان باقی مانده : "+s);
            label.setStyle("-fx-background-color: #2ce026");
            label1.setStyle("-fx-background-color: #e6ff14");
            label.setPrefSize(250,100);
            label.setAlignment(Pos.CENTER);
            label.setPadding(new Insets(5));
            label.setFont(Font.font("serif",20));
            label1.setPrefSize(250,100);
            label1.setAlignment(Pos.CENTER);
            label1.setPadding(new Insets(5));
            label1.setFont(Font.font("serif",20));
            VBox vbox = new VBox(imageView, text,label,label1);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefSize(350,350);
            grid2.add(vbox, i, 0);
        }
    }

    private void goToAuctionPage(Product product, int index) throws IOException {

        Information.productAuction=product;

        Information.INDEXAuction=index;

        Scene scene = new Scene(Methods.loader("auctionCustomer.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }


    @FXML
    public void totalProduct(ActionEvent actionEvent) throws FileNotFoundException {
        category=0;
        switch (mode){
            case 0:setProduct(0);break;
            case 1:setProduct(1);break;
            case 2:setProduct(2);break;
            case 3:setProduct(3);break;
            case 4:break;
            case 5:setProduct(5);break;
        }
    }

    public void gotoChat(MouseEvent mouseEvent) throws SQLException, IOException {

        if (Information.isLogin() && (Information.getRole()==1 || Information.getRole()==2)) new ChatPage().start(Methods.stage,1,1);
        else Methods.notification("ابتدا وارد حساب کاربری خود شوید    ",4);
    }




}
