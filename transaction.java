package com.example.OnlineShop;

import java.time.LocalDate;

public class transaction {

    private String customer;
    private String seller;
    private String codeOfProduct;
    private String date;
    private String code;
    private String address;
    private int numberProduct;
    private String phoneNumber;
    private double price;

    public String getCustomer() {
        return customer;
    }

    public String getSeller() {
        return seller;
    }

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getPrice() {
        return price;
    }

    public transaction(String customer, String seller, String codeOfProduct, String code, String address, String date, int numberProduct, String phoneNumber,double price){
        this.customer=customer;
        this.seller=seller;
        this.codeOfProduct=codeOfProduct;
        this.numberProduct=numberProduct;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.code=code;
        this.date=date;
        this.price=price;
    }
}
