package com.example.OnlineShop;

public class Admin {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String userName;
    private String password;
    private String email;
    private static double money;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public static double getMoney() {
        return money;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void setMoney(double money) {
        Admin.money = money;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public Admin(String firstName, String lastName, String phoneNumber, String userName, String password, String email){
        setRole("admin");
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserName(userName);
        setPassword(password);
        setEmail(email);
    }

}
