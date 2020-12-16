package com.example.trashsolution;

public class User {
    public String id;
    protected String password;

    User(){
        this.id = new String("");
        this.password = new String("");
    }
    User(String id, String password){
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }
}
