package com.nyok.bottom_navigation.menu_dalam;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nyok.bottom_navigation.MinumanAdapter;
import com.nyok.bottom_navigation.ModelApi.MinumanResponse;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.CategoryApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinumanActivity extends AppCompatActivity {

    private RecyclerView rvMinuman;
    private ProgressBar progressBar;
    private MinumanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minuman);

        rvMinuman = findViewById(R.id.rv_minuman);
        progressBar = findViewById(R.id.progressBar);

        rvMinuman.setLayoutManager(new LinearLayoutManager(this));

        getDataMinuman();
    }

    private void getDataMinuman() {
        progressBar.setVisibility(View.VISIBLE);

        CategoryApiService apiService = ApiClient.getClient().create(CategoryApiService.class);
        Call<MinumanResponse> call = apiService.getMinuman();

        call.enqueue(new Callback<MinumanResponse>() {
            @Override
            public void onResponse(Call<MinumanResponse> call, Response<MinumanResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    adapter = new MinumanAdapter(response.body().getData());
                    rvMinuman.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MinumanResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                // Handle errors if needed
            }
        });
    }
}
