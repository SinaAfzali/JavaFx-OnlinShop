package com.example.OnlineShop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public final class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {


        Database.readPerson(Information.getPersons());

        Database.readProduct(Information.getProducts());

        Database.readTransaction(Information.getTransactions());

        Database.readMessage(Information.getMessages());

        Database.readComment(Information.getComments());

        Database.readPoints(Information.getPoints());

        Database.readAuctions(Information.getAuctions());

        Information.getPersons().get(1).setMoney(1000000);



        wareHouse wareHouse=new wareHouse("FUM WareHouse","Mashhad","sina1382");
        Information.getWareHouses().add(wareHouse);



        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}