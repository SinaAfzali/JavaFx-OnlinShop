package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public final class wareHouseController implements Initializable {

    public Menu menu;
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
    private AnchorPane warehousePane;

    boolean change=false;
    @FXML
    void addWareHouse(ActionEvent event) {
        if (!change) {
            infoPane.setVisible(true);
            infoPane.setStyle("-fx-background-color: #07aef5");
            change=true;
        }
        else if (change){
            infoPane.setVisible(false);
            infoPane.setStyle("-fx-background-color: null");
            change=false;
        }

    }

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void createButton(ActionEvent event) {

        boolean permission=true;

        for (int i=0;i<Information.getWareHouses().size();i++){
            if (namefield.getText().equals(Information.getWareHouses().get(i).getName())) {
                permission = false;
                Methods.notification("انباری با این نام قبلا ایجاد شده است /  از نام دیگری استفاده کنید",4);
                break;
            }
        }

        if (addressField.getText().length()==0) {
            permission = false;
            Methods.notification("لطفا آدرس انبار را وارد کنید",3);
        }

        if (permission){
            Information.getWareHouses().add(new wareHouse(namefield.getText(), addressField.getText(), Information.getUserName()));

            infoPane.setVisible(false);

            setWarehouse();

            change = false;

            MenuItem menuItem=new MenuItem(namefield.getText());
            menu.getItems().add(menuItem);
            int index=Information.getWareHouses().size()-1;
            menuItem.setOnAction(e ->{
                action2(index);
            });

            namefield.setText("");
            addressField.setText("");



        }
    }

    public void setWarehouse(){


        for (int i=0;i<Information.getProducts().size();i++){
            int j=i;
            grid.getChildren().removeIf(node -> GridPane.getRowIndex(node)==j && GridPane.getColumnIndex(node)==j);
        }

        for (int i=0;i<Information.getWareHouses().size();i++){

            warehousePane.setPrefHeight((i+1)*60);
            grid.setPrefHeight((i+1)*60);
            Button numberLabel=new Button(String.valueOf(Information.getWareHouses().get(i).getNumberProduct()));
            numberLabel.setPrefSize(120,60);
            Button managerLabel=new Button(Information.getWareHouses().get(i).getManager());
            managerLabel.setPrefSize(150,60);
            Button addressLabel=new Button(Information.getWareHouses().get(i).getAddress());
            addressLabel.setPrefSize(250,60);
            Button nameLabel=new Button(Information.getWareHouses().get(i).getName());
            nameLabel.setPrefSize(150,60);

            int index=i;
            nameLabel.setOnAction(e ->{
                action(Information.getWareHouses().get(index).getName());
            });
            addressLabel.setOnAction(e ->{
                action(Information.getWareHouses().get(index).getName());
            });
            managerLabel.setOnAction(e ->{
                action(Information.getWareHouses().get(index).getName());
            });
            numberLabel.setOnAction(e ->{
                action(Information.getWareHouses().get(index).getName());
            });

            HBox hBox=new HBox(nameLabel,addressLabel,managerLabel,numberLabel);



            hBox.setPadding(new Insets(5));
            grid.add(hBox,0,i);

        }
    }

    public void action(String name){
        Methods.stage.close();
        new pieChart(Methods.numCInWH(Methods.returnCategory(1),name),Methods.numCInWH(Methods.returnCategory(2),name),
                Methods.numCInWH(Methods.returnCategory(3),name),Methods.numCInWH(Methods.returnCategory(4),name),
                Methods.numCInWH(Methods.returnCategory(5),name),Methods.numCInWH(Methods.returnCategory(6),name),
                Methods.numCInWH(Methods.returnCategory(7),name),Methods.numCInWH(Methods.returnCategory(8),name),
                Methods.numCInWH(Methods.returnCategory(9),name),Methods.numCInWH(Methods.returnCategory(10),name));


    }

    public void action2(int index){
        Information.setDefaultWareHouse(Information.getWareHouses().get(index).getName());
        menu.setText(Information.getDefaultWareHouse());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWarehouse();
    }

    public void fumWareHouse(ActionEvent actionEvent) {
        Information.setDefaultWareHouse("FUM WareHouse");
        menu.setText("FUM WareHouse");
    }
}
