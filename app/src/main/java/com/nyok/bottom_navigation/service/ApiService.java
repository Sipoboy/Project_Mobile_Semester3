package com.nyok.bottom_navigation.service;

import com.nyok.bottom_navigation.ModelApi.LoginResponse;
import com.nyok.bottom_navigation.ModelApi.UpdateRequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("login/index")
    Call<LoginResponse> login(
            @Field("EmailorUser") String emailOrUser,
            @Field("password") String password,
            @Field("is_api") String isApi // Tambahkan parameter is_api
    );

    Call<Void> updateUser(UpdateRequest request);
}
