package com.example.OnlineShop;

import java.util.ArrayList;

public final class Information {

    private static ArrayList<Person>persons=new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<wareHouse> wareHouses = new ArrayList<>();
    private static ArrayList<Comment>comments=new ArrayList<>();
    private static ArrayList<transaction>transactions=new ArrayList<>();

    private static ArrayList<message>messages=new ArrayList<>();

    private static ArrayList<point>points=new ArrayList<>();

    private static ArrayList<auction>auctions=new ArrayList<>();


    public static ArrayList<Person> getPersons() {
        return persons;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<wareHouse> getWareHouses() {
        return wareHouses;
    }

    public static ArrayList<Comment> getComments() {
        return comments;
    }

    public static ArrayList<transaction> getTransactions() {
        return transactions;
    }

    public static ArrayList<message> getMessages() {
        return messages;
    }

    public static ArrayList<point> getPoints() {
        return points;
    }

    public static ArrayList<auction> getAuctions() {
        return auctions;
    }

    private static String userName;

    private static String defaultWareHouse="FUM WareHouse";

    public static void setDefaultWareHouse(String defaultWareHouse) {
        Information.defaultWareHouse = defaultWareHouse;
    }

    public static String getDefaultWareHouse() {
        return defaultWareHouse;
    }

    private static int role;

    public static int getRole() {
        return role;
    }

    public static void setRole(int role) {
        Information.role = role;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Information.userName = userName;
    }

    private static boolean Login=false;

    public static boolean isLogin() {
        return Login;
    }

    public static void setLogin(boolean login) {
        Login = login;
    }

    private static int totalPrice;

    public static void setTotalPrice(int totalPrice) {
        Information.totalPrice = totalPrice;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }
    public static String phoneNumber;
    public static String address;

    public static int backLogin;

    public static int backDiscount;
    public static Product product;

    public static int INDEX;

    public static String userChat;

    public static int roleChat;


    public static Product productAuction;

    public static int INDEXAuction;



}
