package com.example.googleclassroom.utility;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String messageType;

    public User(String messageType, String username, String pass) {
        this.messageType = messageType;
        this.username = username;
        this.password = pass;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getMessageType() {
        return messageType;
    }
}
