package com.nyok.bottom_navigation.ModelApi;

import com.nyok.bottom_navigation.model.Minuman;

import java.util.List;

public class MinumanResponse {
    private String status; // Status dari respons API
    private List<Minuman> data; // List produk Minuman
    private String message; // Pesan dari respons API

    // Getter dan Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Minuman> getData() {
        return data;
    }

    public void setData(List<Minuman> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
