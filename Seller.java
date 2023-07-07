package com.example.OnlineShop;


public final class Seller extends Person{

    public Seller(String firstName, String lastName, String phoneNumber, String userName, String password, String email){
        setRole("فروشنده");
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserName(userName);
        setPassword(password);
        setEmail(email);
    }
}
