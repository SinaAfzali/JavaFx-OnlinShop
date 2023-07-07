package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class AdvertiseController {

    @FXML
    private Text setPrice;

    @FXML
    private ImageView importedImageView;

    @FXML
    private ImageView setProductImage;

    @FXML
    void backIcon(MouseEvent event) {

    }

    @FXML
    void setAdvertiseConfirm(ActionEvent event) {

    }

    @FXML
    void setStudy(ActionEvent event) {

    }
    @FXML
    void importImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Update the new ImageView with the selected image
            Image image = new Image(selectedFile.toURI().toString());
            importedImageView.setImage(image);
        }
    }

}
