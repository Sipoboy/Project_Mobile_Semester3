package com.nyok.bottom_navigation.service;

import com.nyok.bottom_navigation.ModelApi.ProdukResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdukApiService {
    @GET("ProdukApi/getAllProduk")
    Call<ProdukResponse> getProdukList(
            @Query("kategori") String kategori // Parameter kategori yang diperlukan
    );
}
