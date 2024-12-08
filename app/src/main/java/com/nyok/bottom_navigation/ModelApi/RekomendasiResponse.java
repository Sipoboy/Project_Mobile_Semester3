package com.nyok.bottom_navigation.ModelApi;

import com.google.gson.annotations.SerializedName;
import com.nyok.bottom_navigation.model.Rekomendasi;

import java.util.List;

public class RekomendasiResponse {
    @SerializedName("status")
    private String status;  // Ubah menjadi String karena "success" adalah string

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Rekomendasi> data;

    // Getter dan Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Rekomendasi> getData() {
        return data;
    }

    public void setData(List<Rekomendasi> data) {
        this.data = data;
    }
}
