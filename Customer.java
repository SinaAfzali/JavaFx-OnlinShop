package com.example.OnlineShop;

import java.util.ArrayList;

public final class Customer extends Person{

    private final ArrayList<String>discountCode=new ArrayList<>();

    public ArrayList<String> getDiscountCode() {
        return discountCode;
    }

    public void addDiscountCode(String discountCode) {
        if (getDiscountCode().size()<6){
            getDiscountCode().add(discountCode);
        }
    }
    public Customer(String firstName, String lastName, String phoneNumber, String userName, String password, String email){
        setRole("خریدار");
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserName(userName);
        setPassword(password);
        setEmail(email);
    }

}
