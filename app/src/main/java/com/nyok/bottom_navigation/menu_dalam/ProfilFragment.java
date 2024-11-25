package com.nyok.bottom_navigation.menu_dalam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.adapter.ProdukAdapter;
import com.nyok.bottom_navigation.model.Produk;
import com.nyok.bottom_navigation.ModelApi.ProdukResponse;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.ProdukApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProdukAdapter adapter;
    private List<Produk> produkList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recyclerViewProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize produkList and adapter
        produkList = new ArrayList<>();
        adapter = new ProdukAdapter(produkList);
        recyclerView.setAdapter(adapter);

        // Fetch products from API
        fetchProductsFromApi("kategori_anda"); // Gantilah "kategori_anda" dengan kategori yang sesuai

        return view;
    }

    private void fetchProductsFromApi(String kategori) {
        // Membuat instance Retrofit
        ProdukApiService apiService = ApiClient.getClient().create(ProdukApiService.class);

        // Membuat request untuk mengambil data produk dengan kategori yang ditentukan
        Call<ProdukResponse> call = apiService.getProdukList(kategori);

        // Menangani respons API
        call.enqueue(new Callback<ProdukResponse>() {
            @Override
            public void onResponse(Call<ProdukResponse> call, Response<ProdukResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProdukResponse produkResponse = response.body();

                    if ("success".equals(produkResponse.getStatus())) {
                        // Menambahkan data produk ke produkList dan memperbarui adapter
                        produkList.clear();
                        produkList.addAll(produkResponse.getData()); // Ambil list produk dari `data`
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProdukResponse> call, Throwable t) {
                // Menangani kesalahan koneksi atau lainnya
                Toast.makeText(getContext(), "Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
