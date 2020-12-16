package com.example.trashsolution;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public String address;
    public Usage usage;

    Customer(){
        this.address = new String("");
        this.usage = new Usage();
    }
    Customer(String id, String password, String address, Usage usage){
        this.id = id;
        this.password = password;
        this.address = address;
        this.usage = usage;
    }
    public void setCustomerInfo(String id, String password, String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public Usage getsUsage(){
        return usage;
    }
}
