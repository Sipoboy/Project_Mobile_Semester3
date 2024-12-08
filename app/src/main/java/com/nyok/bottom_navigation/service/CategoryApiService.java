package com.nyok.bottom_navigation.service;

import com.nyok.bottom_navigation.ModelApi.MakananResponse;
import com.nyok.bottom_navigation.ModelApi.MinumanResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApiService {
    @GET("produkApi/getMakanan")
    Call<MakananResponse> getMakanan(); // Menggunakan MakananResponse

    @GET("produkApi/getMinuman")
    Call<MinumanResponse> getMinuman(); // Menggunakan MinumanResponse
}
