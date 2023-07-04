package com.example.OnlineShop;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{
    String massage="";

    public server(String message) throws IOException {
       this.massage=message;
    }

    public void started(String message){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(56816);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream = null;
        try {
            outputStream = clientSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        try {
            writer.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
       started(massage);
    }
}
