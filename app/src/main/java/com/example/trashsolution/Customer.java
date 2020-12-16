package com.example.trashsolution;

public class Customer extends User {
    public String address;
    public Usage usage;

    Customer(){
        this.address = new String("");
        this.usage = new Usage();
    }
    Customer(String address, Usage usage){
        this.address = address;
        this.usage = usage;
    }
    public void setCustomerInfo(String id, String password, String address){
        this.address = address;
        this.id = id;
        this.password = password;
    }
    public String getAddress(){
        return address;
    }
    public Usage getsUsage(){
        return usage;
    }
}
