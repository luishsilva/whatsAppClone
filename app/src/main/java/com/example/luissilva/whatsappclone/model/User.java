package com.example.luissilva.whatsappclone.model;

import com.google.firebase.database.Exclude;

/**
 * Created by luissilva on 21/01/18.
 */

public class User {

    private String id;
    private String name;
    private String email;
    private String pass;

    public User() {
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
