package com.nyok.bottom_navigation.menu_dalam;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyok.bottom_navigation.MakananAdapter;
import com.nyok.bottom_navigation.ModelApi.MakananResponse;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.CategoryApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakananActivity extends AppCompatActivity {

    private RecyclerView rvMakanan;
    private ProgressBar progressBar;
    private MakananAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makanan);

        rvMakanan = findViewById(R.id.rv_makanan);
        progressBar = findViewById(R.id.progressBar);

        rvMakanan.setLayoutManager(new LinearLayoutManager(this));

        getDataMakanan();
    }

    private void getDataMakanan() {
        progressBar.setVisibility(View.VISIBLE);

        CategoryApiService apiService = ApiClient.getClient().create(CategoryApiService.class);
        Call<MakananResponse> call = apiService.getMakanan();

        call.enqueue(new Callback<MakananResponse>() {
            @Override
            public void onResponse(Call<MakananResponse> call, Response<MakananResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    adapter = new MakananAdapter(response.body().getData());
                    rvMakanan.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MakananResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                // Handle errors if needed
            }
        });
    }
}
