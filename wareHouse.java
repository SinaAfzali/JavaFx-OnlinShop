package com.example.OnlineShop;

public final class wareHouse {
    private String name;
    private String address;
    private String manager;


    public void setName(String name) {
        this.name = name;
    }

    public int getNumberProduct() {
        int count=0;
       for (int i=0;i<Information.getProducts().size();i++){
           if (Information.getProducts().get(i).getWareHouse().equals(getName()))count++;
       }
       return count;
    }

    public String getManager() {
        return manager;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }



    public String getAddress() {
        return address;
    }


    public wareHouse(String name,String address,String manager){
        this.name=name;
        this.address=address;
        this.manager=manager;
    }



}
