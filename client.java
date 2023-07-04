package com.example.OnlineShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;

public class client extends Thread{

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 56816);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String message = null;
        try {
            message = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int rol=0;
        if (Information.roleChat==1)rol=3;
        if(Information.roleChat==2)rol=4;
        if (Information.getRole()==1)rol=1;
        if (Information.getRole()==2)rol=2;
        message message2=new message(Information.userChat,rol,message);
        Information.getMessages().add(message2);
        try {
            Database.addMessage(message2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
