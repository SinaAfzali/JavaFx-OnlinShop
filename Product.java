package com.example.OnlineShop;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public final class Product {


    //نام محصول
    private String name;
    //تعداد محصول
    private int quantity;
    //قیمت محصول
    private double price;
    //امتیاز محصول
    private double score=0;

    private int countScore=0;
    //آدرس عکس محصول
    private String addressImage;
    //دسته بندی محصول
    private String category;

    //توضیحات محصول
    private String description;

    //تعداد در سبد خرید
    private int numberInCart=0;

    //فروشنده
    private String userNameSeller;

    //انبار
    private String wareHouse;

    private String codeOfProduct;

    private long timeAdding;


    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public int getCountScore() {
        return countScore;
    }

    public long getTimeAdding() {
        return timeAdding;
    }

    public void setCodeOfProduct(String codeOfProduct) {
        this.codeOfProduct = codeOfProduct;
    }

    public Product(String name, int quantity, double price, String addressImage, String category, String description, String userNameSeller){
        timeAdding=System.currentTimeMillis();
        numberInCart=0;
        codeOfProduct=Methods.codeRandomString();
        setName(name);
        setQuantity(quantity);
        setPrice(price);
        setAddressImage(addressImage);
        setCategory(category);
        setDescription(description);
        setUserNameSeller(userNameSeller);
        setWareHouse(Information.getDefaultWareHouse());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setCountScore(int countScore) {
        this.countScore = countScore;
    }

    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserNameSeller(String userNameSeller) {
        this.userNameSeller = userNameSeller;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getScore() {
        return score;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAddressImage() {
        return addressImage;
    }

    public String getCategory() {
        return category;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public String getDescription() {
        return description;
    }

    public String getUserNameSeller() {
        return userNameSeller;
    }

    public int getNumberInCart() {
        return numberInCart;
    }
    public DoubleProperty priceProperty() {
        return new SimpleDoubleProperty(price);
    }
}
