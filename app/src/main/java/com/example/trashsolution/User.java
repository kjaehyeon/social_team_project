package com.example.trashsolution;

import java.io.Serializable;

public class User implements Serializable {
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
