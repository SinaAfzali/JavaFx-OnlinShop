package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class reportController {

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Label guidedSupportLabel;

    @FXML
    void returnImage(MouseEvent event) {
        // Handle the return image event
    }

    @FXML
    void sendMessage(ActionEvent event) {
        String message = messageTextArea.getText();
        // Process the message (e.g., send it to the server, store it in a database, etc.)
    }

    @FXML
    void selectIssue(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String issueText = selectedButton.getText();
        guidedSupportLabel.setText(issueText);
        // Display the selected issue's solution or provide relevant guidance
    }
}
