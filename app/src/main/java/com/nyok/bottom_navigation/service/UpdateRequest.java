package com.nyok.bottom_navigation.service;

public class UpdateRequest {
    private String username;
    private String password;

    public UpdateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
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
}
