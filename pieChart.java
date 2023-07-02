package com.example.OnlineShop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class pieChart{
    public pieChart(int c1,int c2,int c3, int c4,int c5,int c6,int c7,int c8, int c9,int c10){
        int x=c1+c2+c3+c4+c6+c7+c8+c9+c10;
        DecimalFormat df = new DecimalFormat("#.#");
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("نوشیدنی"+"( "+c1+"  ,  "+df.format(c1/(float)x*100)+"%"+" )",c1),
                new PieChart.Data("صبحانه"+"( "+c2+"  ,  "+df.format(c2/(float)x*100)+"%"+" )",c2),
                new PieChart.Data("کنسرو و غذای آماده"+"( "+c3+"  ,  "+df.format(c3/(float)x*100)+"%"+" )",c3),
                new PieChart.Data("لبنیات"+"( "+c4+"  ,  "+df.format(c4/(float)x*100)+"%"+" )",c4),
                new PieChart.Data("میوه و سبزیجات"+"( "+c5+"  ,  "+df.format(c5/(float)x*100)+"%"+" )",c5),
                new PieChart.Data("خواروبار"+"( "+c6+"  ,  "+df.format(c6/(float)x*100)+"%"+" )",c6),
                new PieChart.Data("آجیل و خشکبار"+"( "+c7+"  ,  "+df.format(c7/(float)x*100)+"%"+" )",c7),
                new PieChart.Data("محصولات پروتیینی"+"( "+c8+"  ,  "+df.format(c8/(float)x*100)+"%"+" )",c8),
                new PieChart.Data("تنقلات"+"( "+c9+"  ,  "+df.format(c9/(float)x*100)+"%"+" )",c9),
                new PieChart.Data("چاشنی و ادویه جات"+"( "+c10+"  ,  "+df.format(c10/(float)x*100)+"%"+" )",c10)
        );
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("تعداد کل محصولات : "+x);

        pieChart.setPrefSize(600,600);
        pieChart.setLayoutX(465);

        Group root=new Group(pieChart);

        Scene scene = new Scene(root, 1530, 795);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            Scene scene1;
            try {
                scene1 = new Scene(Methods.loader("wareHouse.fxml").load(), 500, 600);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Methods.stage.setScene(scene1);
            Methods.stage.setFullScreen(true);
            Methods.stage.show();
        });
    }
}
