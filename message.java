package com.example.OnlineShop;

public class message {

    private String userSender;

    private int roleSender;

    private String text;

    private int read;

    private int time;

    public String getUserSender() {
        return userSender;
    }

    public int getRoleSender() {
        return roleSender;
    }

    public String getText() {
        return text;
    }

    public int getRead() {
        return read;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public message(String userSender, int roleSender, String text){
        this.userSender=userSender;
        this.roleSender=roleSender;
        this.text=text;
        time= (int) (System.currentTimeMillis()/1000000);
        read=1;
    }

}
