package com.nyok.bottom_navigation.ModelApi;

public class LoginResponse {
    private String status;
    private String message;
    private String role;
    private String username;
    private String password;// Tambahkan jika belum ada

    // Getter
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() { // Pastikan getter ini ada
        return username;
    }
    public String getPassword() {
        return password;
    }
}

