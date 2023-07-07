package com.example.OnlineShop;

public class point {

    private String userName;
    private String codeOfProduct;

    public String getUserName() {
        return userName;
    }

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public point(String userName, String codeOfProduct){

        this.userName=userName;

        this.codeOfProduct=codeOfProduct;

    }
}
