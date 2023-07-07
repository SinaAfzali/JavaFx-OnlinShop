package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalesRecordsController implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private Label addressLabel;

    @FXML
    private Button createButton;

    @FXML
    private GridPane grid;

    @FXML
    private Pane infoPane;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField namefield;

    @FXML
    private Label setTitle;

    @FXML
    private AnchorPane warehousePane;

    @FXML
    void back(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("sellerAccount.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void createButton(ActionEvent event) {

    }

    public void setTransaction(){


        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j && GridPane.getColumnIndex(node)==j);
        }

        ArrayList<String> records=new ArrayList<>();

        int x=0;

        for (int i = Information.getTransactions().size() - 1; i >= 0; i--) {
            int count=0;
            boolean permission=true;

            for (String record : records) {
                if (Information.getTransactions().get(i).getCode().equals(record)) {
                    permission = false;
                    break;
                }
            }

            if (!Information.getTransactions().get(i).getSeller().equals(Information.getUserName())){
                permission=false;
            }


            if (permission){


                count=0;
                String receipt="( رسید خرید از فروشگاه NON SHOP )"+"\n\n";

                records.add(Information.getTransactions().get(i).getCode());

                String products=" : محصولات خریداری شده "+"\n\n";
                String name="";
                double price=0;
                double price2=0;
                int w=0;

                for (int k=0;k<Information.getTransactions().size();k++){
                    name="";
                    price=0;

                    if (Information.getTransactions().get(k).getCode().equals(Information.getTransactions().get(i).getCode())){
                        for (int z=0;z<Information.getProducts().size();z++){
                            if (Information.getTransactions().get(k).getCodeOfProduct().equals(Information.getProducts().get(z).getCodeOfProduct())){
                                name=Information.getProducts().get(z).getName();
                                price=Information.getProducts().get(z).getPrice()*Information.getTransactions().get(k).getNumberProduct();
                                break;
                            }
                        }

                        price2+=price;
                        w++;
                        products +=" محصول شماره "+w+" : "+"\n"+ " نام محصول : "+name+"\n"+Methods.moneyStandard(price/Information.getTransactions().get(k).getNumberProduct())+" : قیمت محصول "+"\n"+Information.getTransactions().get(k).getNumberProduct()+" : تعداد "+
                                "\n"+Information.getTransactions().get(k).getCodeOfProduct()+" : کد محصول "+"\n"+
                                Information.getTransactions().get(k).getCustomer()+ " : نام کاربری خریدار "+"\n\n\n";

                        count+=Information.getTransactions().get(k).getNumberProduct();
                    }
                }

                String f=Information.getTransactions().get(i).getSeller()+" : نام کاربری فروشنده "+"\n"+Information.getTransactions().get(i).getDate()+" : تاریخ تراکنش "+"\n"+
                        count+" : تعداد محصولات فروخته شده "+"\n"+ Methods.moneyStandard(price2)+" : مبلغ دریافتی "+"\n"+Information.getTransactions().get(i).getCode()+" : کد پیگیری"+"\n"+"\n"+ "\n";

                receipt+=f;

                receipt+=products;


                warehousePane.setPrefHeight((x + 1) * 60);
                grid.setPrefHeight((x + 1) * 60);
                x++;
                Button etcLabel = new Button(" جزییات ");
                etcLabel.setPrefSize(120, 60);
                Button dateLabel = new Button(Information.getTransactions().get(i).getDate());
                dateLabel.setPrefSize(150, 60);
                Button priceLabel = new Button(Methods.moneyStandard(price2)+" تومان ");
                priceLabel.setPrefSize(250, 60);
                Button numberLabel = new Button(count+" عدد ");
                numberLabel.setPrefSize(150, 60);


                String s=receipt;
                String c=Information.getTransactions().get(i).getCode();
                etcLabel.setOnAction(e -> {
                    try {
                        action(s,c);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                HBox hBox = new HBox(numberLabel, priceLabel, dateLabel, etcLabel);


                hBox.setPadding(new Insets(5));
                grid.add(hBox, 0, x);

            }
        }

    }




    public void action(String receipt ,String code) throws IOException {
        new CreatingTransactionReceipt(receipt,code,2);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTransaction();
    }
}
