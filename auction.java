package com.example.OnlineShop;

public class auction {

    private String codeOfProduct;
    private String currentCustomer;
    private int basicPrice;
    private int maxPrice;
    private int count;
    private int time;
    private int timeStart;

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public String getCurrentCustomer() {
        return currentCustomer;
    }

    public int getBasicPrice() {
        return basicPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getCount() {
        return count;
    }

    public int getTime() {
        return time;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setCurrentCustomer(String currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice=maxPrice;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public auction(String codeOfProduct, int basicPrice, int count, int time){

        this.codeOfProduct=codeOfProduct;

        this.basicPrice=basicPrice;

        this.count=count;

        this.time=time;

        timeStart= (int) (System.currentTimeMillis()/100000);

        maxPrice=0;

        currentCustomer=".";


    }



}
