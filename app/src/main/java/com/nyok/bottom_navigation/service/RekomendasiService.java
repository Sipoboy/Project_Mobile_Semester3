package com.nyok.bottom_navigation.service;

import com.nyok.bottom_navigation.ModelApi.RekomendasiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RekomendasiService {

    @GET("ProdukApi/getRekomendasiProduk")  // Ganti dengan path API yang sesuai
    Call<RekomendasiResponse> getRekomendasiProduk();
}
