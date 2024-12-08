package com.nyok.bottom_navigation.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nyok.bottom_navigation.ModelApi.MakananResponse
import com.nyok.bottom_navigation.ModelApi.MinumanResponse
import com.nyok.bottom_navigation.ModelApi.RekomendasiResponse
import com.nyok.bottom_navigation.R
import com.nyok.bottom_navigation.service.ApiClient
import com.nyok.bottom_navigation.service.CategoryApiService
import com.nyok.bottom_navigation.service.RekomendasiService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    // LiveData untuk banner
    private val _banners = MutableLiveData<List<SliderModel>>()
    val banners: LiveData<List<SliderModel>>
        get() = _banners

    // LiveData untuk rekomendasi
    private val _recommendations = MutableLiveData<List<Rekomendasi>>()
    val recommendations: LiveData<List<Rekomendasi>>
        get() = _recommendations

    // Metode untuk memuat banner
    fun loadBanners() {
        val sliderModels: MutableList<SliderModel> = ArrayList()
        sliderModels.add(SliderModel(R.drawable.banner1)) // Ganti dengan gambar yang sesuai
        sliderModels.add(SliderModel(R.drawable.banner2)) // Ganti dengan gambar yang sesuai

        _banners.value = sliderModels
    }

    // Metode untuk memuat rekomendasi dari API
    fun loadRecommendations() {
        val apiClient = ApiClient.getClient().create(RekomendasiService::class.java)
        val call = apiClient.getRekomendasiProduk()

        call.enqueue(object : Callback<RekomendasiResponse> {
            override fun onResponse(
                call: Call<RekomendasiResponse>,
                response: Response<RekomendasiResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val rekomendasiResponse = response.body()
                    Log.d("MainViewModel", "Response: $rekomendasiResponse")

                    if (rekomendasiResponse?.status == "success") {
                        _recommendations.value = rekomendasiResponse.data
                        Log.d("MainViewModel", "Recommendations loaded successfully")
                    } else {
                        Log.e(
                            "MainViewModel",
                            "Failed to load recommendations: ${rekomendasiResponse?.message}"
                        )
                    }
                } else {
                    Log.e("MainViewModel", "API response error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RekomendasiResponse>, t: Throwable) {
                Log.e("MainViewModel", "Error loading recommendations: ${t.message}")
            }
        })
    }
}