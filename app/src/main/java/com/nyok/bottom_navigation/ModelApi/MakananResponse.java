package com.nyok.bottom_navigation.ModelApi;

import com.nyok.bottom_navigation.model.Makanan;

import java.util.List;

public class MakananResponse {
    private String status; // Status dari respons API
    private List<Makanan> data; // List produk makanan
    private String message; // Pesan dari respons API

    // Getter dan Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Makanan> getData() {
        return data;
    }

    public void setData(List<Makanan> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
