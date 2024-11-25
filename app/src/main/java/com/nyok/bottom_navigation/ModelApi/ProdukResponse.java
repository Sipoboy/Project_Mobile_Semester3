package com.nyok.bottom_navigation.ModelApi;

// Produk.java

import com.google.gson.annotations.SerializedName;
import com.nyok.bottom_navigation.model.Produk;

import java.util.List;

public class ProdukResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Produk> data;

    public String getStatus() {
        return status;
    }

    public List<Produk> getData() {
        return data;
    }
}
