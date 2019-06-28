package com.example.googleclassroom;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    ArrayList<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> index = new HashMap<>();

    public User(String username, String password) {
        index.put(username, password);
        this.users.add(index);
    }
}
