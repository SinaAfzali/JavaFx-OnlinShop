package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class LoginController implements Initializable {

    public Button admin;
    int role=1;

    @FXML
    public Button seller;
    @FXML
    public Button customer;
    @FXML
    private TextField codeField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passField;

    @FXML
    private ImageView randomImage;

    @FXML
    private TextField userField;
    @FXML
    private ImageView back;


    private String image = "src/main/resources/com/example/image/91758.png";


    @FXML
    void loginButton(ActionEvent event) throws IOException {
        userField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        passField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        codeField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");

            boolean permission = false;
            boolean permissionCodeRand = codeField.getText().equals(Methods.codeImageRand(image));


        if (role==1){
            for (int i=0;i<Information.getPersons().size();i++){
                if ( Information.getPersons().get(i) instanceof Customer && Information.getPersons().get(i).getUserName().equals(userField.getText()) && Information.getPersons().get(i).getPassword().equals(passField.getText()))permission=true;
            }
        }
        if (role==2){
            for (int i=0;i<Information.getPersons().size();i++){
                if ( Information.getPersons().get(i) instanceof Seller && Information.getPersons().get(i).getUserName().equals(userField.getText()) && Information.getPersons().get(i).getPassword().equals(passField.getText()))permission=true;
            }
        }

        if (role==3){
            if (userField.getText().equals("sina") && passField.getText().equals("1234")){
                Information.setLogin(true);
                Information.setUserName("sina");
                Information.setRole(role);
                Scene scene = new Scene(Methods.loader("AdminAccount.fxml").load(), 500, 600);
                Methods.stage.setScene(scene);
                Methods.stage.setFullScreen(true);
                Methods.stage.show();
            }
        }

        if (permission && permissionCodeRand) {
            Information.setUserName(userField.getText());
            Information.userChat=userField.getText();
            Information.setRole(role);
            Methods.stage.close();
            Information.setLogin(true);
            if (role==1) {
                if (Information.backLogin==2){
                    Scene scene = new Scene(Methods.loader("cart.fxml").load(), 500, 600);
                    Methods.stage.setScene(scene);
                    Methods.stage.setFullScreen(true);
                    Methods.stage.show();
                }
                if (Information.backLogin==1){
                    Scene scene = new Scene(Methods.loader("CustomerAccount.fxml").load(), 500, 600);
                    Methods.stage.setScene(scene);
                    Methods.stage.setFullScreen(true);
                    Methods.stage.show();
                }
                if (Information.backLogin==3){
                    Scene scene = new Scene(Methods.loader("product.fxml").load(), 500, 600);
                    Methods.stage.setScene(scene);
                    Methods.stage.setFullScreen(true);
                    Methods.stage.show();
                }
                if (Information.backLogin==4){
                    Scene scene = new Scene(Methods.loader("auctionCustomer.fxml").load(), 500, 600);
                    Methods.stage.setScene(scene);
                    Methods.stage.setFullScreen(true);
                    Methods.stage.show();
                }
            }
            else if (role==2){
                Scene scene = new Scene(Methods.loader("sellerAccount.fxml").load(), 500, 600);
                Methods.stage.setScene(scene);
                Methods.stage.setFullScreen(true);
                Methods.stage.show();
            }
        } else if (permission){
            codeField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }else if (permissionCodeRand){
            userField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            passField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }else {
            userField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            passField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
            codeField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
        }
    }

    @FXML
    void refresh(MouseEvent event) throws FileNotFoundException {
        image = Methods.imageRand();
        randomImage.setImage(null);
        randomImage.setImage(new Image(new FileInputStream(image)));
    }

    @FXML
    void forget() throws IOException {
        Methods.stage.close();
        Scene scene = new Scene(Methods.loader("forget.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void signing() throws IOException {
        Methods.stage.close();
        Scene scene = new Scene(Methods.loader("signing.fxml").load(), 500, 600);
        Methods.stage.setTitle("forget");
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void back() throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    public void seller(MouseEvent mouseEvent) {
        seller.setStyle("-fx-background-color: #11DECB");
        customer.setStyle("-fx-background-color: #ffffff");
        admin.setStyle("-fx-background-color: #ffffff");
        role=2;
    }

    public void customer(MouseEvent mouseEvent) {
        customer.setStyle("-fx-background-color: #11DECB");
        seller.setStyle("-fx-background-color: #ffffff");
        admin.setStyle("-fx-background-color: #ffffff");
        role=1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = Methods.imageRand();
        randomImage.setImage(null);
        try {
            randomImage.setImage(new Image(new FileInputStream(image)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void admin(MouseEvent mouseEvent) {
        admin.setStyle("-fx-background-color: #11DECB");
        seller.setStyle("-fx-background-color: #ffffff");
        customer.setStyle("-fx-background-color: #ffffff");
        role=3;
    }
}
