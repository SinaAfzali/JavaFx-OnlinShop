package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class AddProductController {


    @FXML
    private CheckBox beveragesCheck;

    @FXML
    private CheckBox breakfastCheck;

    @FXML
    private CheckBox dairyCheck;

    @FXML
    private CheckBox groceryCheck;

    @FXML
    private CheckBox snacksCheck;

    @FXML
    private CheckBox proteinCheck;

    @FXML
    private CheckBox spicesCheck;

    @FXML
    private CheckBox fruitsCheck;

    @FXML
    private CheckBox readyFoodCheck;

    @FXML
    private CheckBox nutsCheck;





    @FXML
    private CheckBox Check;

    @FXML
    private ImageView backIcon;

    @FXML
    private TextField discriptionOfProductField;

    @FXML
    private TextField nameOfProductField;

    @FXML
    private TextField numberOfProductField;

    @FXML
    private TextField priceOfProductField;

    int category;

    public AddProductController() {
    }

    @FXML
    void beveragesCheck(ActionEvent event) {
        beveragesCheck.setSelected(true);
        category=1;
        CheckboxSelection(category);
    }

    @FXML
    void breakfastCheck(ActionEvent event) {
        breakfastCheck.setSelected(true);
        category=2;
        CheckboxSelection(category);
    }

    @FXML
    void confirmButton(ActionEvent event) throws IOException, SQLException {
        Product product=new Product(nameOfProductField.getText(),Integer.parseInt(numberOfProductField.getText()),Double.parseDouble(priceOfProductField.getText()),
                addressImage,Methods.returnCategory(category),discriptionOfProductField.getText(),Information.getUserName());
        Information.getProducts().add(product);
        Database.addProduct(product);
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }



    @FXML
    void fruitsCheck(ActionEvent event) {
        fruitsCheck.setSelected(true);
        category=5;
        CheckboxSelection(category);
    }

    @FXML
    void groceryCheck(ActionEvent event) {
        groceryCheck.setSelected(true);
        category=6;
        CheckboxSelection(category);
    }

    @FXML
    void nutsCheck(ActionEvent event) {
        nutsCheck.setSelected(true);
        category=7;
        CheckboxSelection(category);
    }

    @FXML
    void proteinCheck(ActionEvent event) {
        proteinCheck.setSelected(true);
        category=8;
        CheckboxSelection(category);
    }

    @FXML
    void readyFoodCheck(ActionEvent event) {
        readyFoodCheck.setSelected(true);
        category=3;
        CheckboxSelection(category);
    }

    @FXML
    void snacksCheck(ActionEvent event) {
        snacksCheck.setSelected(true);
        category=9;
        CheckboxSelection(category);
    }

    @FXML
    void spicesCheck(ActionEvent event) {
        proteinCheck.setSelected(true);
        category=10;
        CheckboxSelection(category);
    }

    @FXML
    private ImageView imagePreview;

    private String addressImage="";
    @FXML
    void importImageButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(Methods.stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imagePreview.setImage(image);
            addressImage="";
            for (int i=6;i<image.getUrl().toString().length();i++){
                addressImage+=image.getUrl().toString().charAt(i);
            }
        }
    }

    @FXML
     void dairyCheck(ActionEvent actionEvent) {
        dairyCheck.setSelected(true);
        category=4;
        CheckboxSelection(category);
    }


    private void CheckboxSelection(int category) {

        // Uncheck all checkboxes except the selected one
        if (category!=1) {
            beveragesCheck.setSelected(false);
        }
        if (category!=2) {
            breakfastCheck.setSelected(false);
        }
        if (category!=3) {
            readyFoodCheck.setSelected(false);
        }
        if (category!=4) {
            dairyCheck.setSelected(false);
        }
        if (category!=5) {
            fruitsCheck.setSelected(false);
        }
        if (category!=6) {
            groceryCheck.setSelected(false);
        }
        if (category!=7) {
            nutsCheck.setSelected(false);
        }
        if (category!=8) {
            proteinCheck.setSelected(false);
        }
        if (category!=9) {
            snacksCheck.setSelected(false);
        }
        if (category!=10) {
            spicesCheck.setSelected(false);
        }
    }

}
