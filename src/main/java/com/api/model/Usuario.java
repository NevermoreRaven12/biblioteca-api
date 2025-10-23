package com.api.model;

import java.util.UUID;

// Usuario.java
public class Usuario {
    private final UUID id = UUID.randomUUID();
    private String username;
    private String password;

    public Usuario(){}
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UUID getId() {
        return id;
    }

    
}
