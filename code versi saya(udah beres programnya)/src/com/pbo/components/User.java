package com.pbo.components;

import java.util.ArrayList;

public class User {
    static public ArrayList<String> users = new ArrayList<String>();
    static public String userNow;
    private String name;

    public User(String name) {
        this.name = name;
        User.users.add(name);
        User.userNow = name;
    }

    public String getName() {
        return this.name;
    }
}
