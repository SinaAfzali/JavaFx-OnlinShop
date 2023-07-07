package com.example.OnlineShop;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class sound extends Thread{

    String path="";

    public sound(String path){
        this.path=path;
    }



    public void run(){
        File soundFile = new File(path);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AudioFormat format = audioIn.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = null;
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            line.open(format);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        line.start();
        int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
        byte[] buffer = new byte[bufferSize];
        int bytesRead = 0;
        while (true) {
            try {
                if (!((bytesRead = audioIn.read(buffer, 0, buffer.length)) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            line.write(buffer, 0, bytesRead);
        }
        line.drain();
        line.stop();
        line.close();
    }

    public static void play(String path){
        new sound(path).start();
    }
}
