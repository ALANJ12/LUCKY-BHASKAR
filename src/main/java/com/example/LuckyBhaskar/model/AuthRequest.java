package com.example.LuckyBhaskar.model;

public class AuthRequest {


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
        private String password;

        // Getters and setters

}
