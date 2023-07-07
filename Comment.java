package com.example.OnlineShop;

public class Comment {
    private String userName;

    private String codeOfProduct;
    private String textComment;
    private int bought;

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public String getUserName() {
        return userName;
    }

    public String getTextComment() {
        return textComment;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public Comment(String userName, String textComment, String codeOfProduct, int bought){
        this.userName=userName;
        this.textComment=textComment;
        this.bought=bought;
        this.codeOfProduct=codeOfProduct;
    }
}
