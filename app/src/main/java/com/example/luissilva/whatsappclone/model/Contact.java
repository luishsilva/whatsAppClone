package com.example.luissilva.whatsappclone.model;

/**
 * Created by luissilva on 01/02/18.
 */

public class Contact {

    private String userIdentifier;
    private String nome;
    private String email;

    public Contact() {
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
