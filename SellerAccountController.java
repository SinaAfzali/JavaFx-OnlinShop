package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public final class SellerAccountController implements Initializable {
    @FXML
    public Button confirmButton;
    @FXML
    public Button cancelButton;
    private boolean editMode = false;

    private String initialEmail;
    private String initialFirstName;
    private String initialLastName;
    private String initialPassword;
    private String initialPhone;
    private String initialRule;
    private String initialUsername;

    private FileChooser fileChooser;



    @FXML
    public void initialize() {
        // Initialization tasks and setup here
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
    }



    @FXML
    private ImageView changeProfile;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label moneyField;

    @FXML
    private TextField passField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField ruleField;

    @FXML
    private TextField userField;

    @FXML
    void addProductButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("addProduct.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void bankAccounts(ActionEvent event) {

    }
//-----------------------------------------------------------
    @FXML
    void cancelButton(ActionEvent event) {
        if (editMode) {
            confirmButton.setVisible(false);
            cancelButton.setVisible(false);
            // Restore initial values
            emailField.setText(initialEmail);
            firstNameField.setText(initialFirstName);
            lastNameField.setText(initialLastName);
            passField.setText(initialPassword);
            phoneField.setText(initialPhone);
            ruleField.setText(initialRule);
            userField.setText(initialUsername);

            // Disable editing
            enableTextFields(false);
            editMode = false;
        }
    }

//---------------------------------------------------------
    @FXML
    void changeInformationButton(ActionEvent event) {
        if (!editMode) {
//            File selectedFile = fileChooser.showOpenDialog(null);
//            if(selectedFile != null){
//
//            }


            cancelButton.setVisible(true);
            confirmButton.setVisible(true);

            // Store initial values
            initialEmail = emailField.getText();
            initialFirstName = firstNameField.getText();
            initialLastName = lastNameField.getText();
            initialPassword = passField.getText();
            initialPhone = phoneField.getText();
            initialUsername = userField.getText();

            // Enable editing
            enableTextFields(true);
            editMode = true;
        }

    }
//-----------------------------------------------------------
    private void enableTextFields(boolean enable) {
        emailField.setEditable(enable);
        firstNameField.setEditable(enable);
        lastNameField.setEditable(enable);
        passField.setEditable(enable);
        phoneField.setEditable(enable);
        userField.setEditable(enable);
    }
//-----------------------------------------------------------

    @FXML
    void confirmButton(ActionEvent event) {

        if (editMode) {
            int x=emailField.getText().indexOf("@gmail.com");
            if ((emailField.getText().length()==0 || !emailField.getText().contains("@gmail.com") || emailField.getText().length()>x+10)) {
                // Display error for invalid email
                emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-radius: 20px; -fx-border-radius: 20px ");

            }else {
                emailField.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-background-radius: 20px; -fx-border-radius: 20px ");
                confirmButton.setVisible(false);
                cancelButton.setVisible(false);
                String email = emailField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = passField.getText();
                String phone = phoneField.getText();
                String rule = ruleField.getText();
                String username = userField.getText();
                enableTextFields(false);
                editMode = false;
            }

        }
    }
//-------------------------------------------------------------
    @FXML
    void creatAuctionButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("Auction.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void returnIcon(MouseEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("MainPage.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void sellInformationButton(ActionEvent event) throws IOException {
        Scene scene = new Scene(Methods.loader("SalesRecords.fxml").load(), 500, 600);
        Methods.stage.setScene(scene);
        Methods.stage.setFullScreen(true);
        Methods.stage.show();
    }

    @FXML
    void takeMoney(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=0;i<Information.getPersons().size();i++){
            if (Information.getPersons().get(i) instanceof Seller && Information.getPersons().get(i).getUserName().equals(Information.getUserName())){
                firstNameField.setText(Information.getPersons().get(i).getFirstName());
                lastNameField.setText(Information.getPersons().get(i).getLastName());
                passField.setText(Information.getPersons().get(i).getPassword());
                emailField.setText(Information.getPersons().get(i).getEmail());
                ruleField.setText(Information.getPersons().get(i).getRole());
                phoneField.setText(Information.getPersons().get(i).getPhoneNumber());
                userField.setText(Information.getPersons().get(i).getUserName());
                moneyField.setText(String.valueOf(Methods.moneyStandard(Information.getPersons().get(i).getMoney())));
                break;
            }
        }
    }

}
