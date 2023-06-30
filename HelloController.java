package com.example.sound;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HelloController {
    @FXML
    private Label welcomeText;

    private MediaPlayer mediaPlayer;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        playMusic();
    }

    private void playMusic() {
        String musicFile = "C:\\Users\\ASUS\\Downloads\\Music\\1.mp3"; // Replace with the actual path to your audio file
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    // Other methods and code...
}
