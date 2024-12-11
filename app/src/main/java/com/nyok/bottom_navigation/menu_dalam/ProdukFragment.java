package com.nyok.bottom_navigation.menu_dalam;

import android.content.Intent;
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

public class ProdukFragment extends Fragment implements ProdukAdapter.OnProdukClickListener {

    private RecyclerView recyclerView;
    private ProdukAdapter adapter;
    private List<Produk> produkList;
    private String kategori; // Variabel kategori

    // Konstruktor default tanpa parameter
    public ProdukFragment() {
        // Konstruktor kosong atau inisialisasi default lainnya
    }

    // Konstruktor dengan kategori
    public ProdukFragment(String kategori) {
        this.kategori = kategori;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment
        View view = inflater.inflate(R.layout.fragment_produk, container, false);

        // Mengambil kategori dari arguments jika ada
        if (getArguments() != null) {
            kategori = getArguments().getString("kategori");
        }

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = view.findViewById(R.id.recyclerViewProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Inisialisasi produkList dan adapter
        produkList = new ArrayList<>();
        adapter = new ProdukAdapter(requireContext(), produkList, this);
        recyclerView.setAdapter(adapter);

        // Fetch produk dari API berdasarkan kategori
        fetchProductsFromApi(kategori);

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
                        produkList.addAll(produkResponse.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProdukResponse> call, Throwable t) {
                // Menangani kesalahan koneksi atau lainnya
                Toast.makeText(requireContext(), "Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProdukClicked(Produk produk) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.addItemToCart(produk);
        }

        // Tampilkan toast
        Toast.makeText(requireContext(), "Item berhasil ditambahkan ke keranjang!", Toast.LENGTH_SHORT).show();


    // Pindahkan produk ke CartFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("produk_keranjang", produk);

        // Gantikan fragment yang ada dengan KeranjangFragment
        KeranjangFragment keranjangFragment = new KeranjangFragment();
        keranjangFragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, keranjangFragment)  // Pastikan R.id.frame_layout sesuai dengan ID container fragment Anda
                .addToBackStack(null)  // Menambah fragment ke back stack untuk navigasi kembali
                .commit();
    }
}
