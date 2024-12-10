package com.nyok.bottom_navigation.menu_dalam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.adapter.KeranjangAdapter;  // Sesuaikan dengan nama adapter yang mendukung KeranjangModel
import com.nyok.bottom_navigation.model.KeranjangModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeranjangFragment extends Fragment {
    private RecyclerView recyclerView;
    private KeranjangAdapter adapter;
    private List<KeranjangModel> keranjangList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);

        recyclerView = view.findViewById(R.id.viewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Load data keranjang
        keranjangList = loadCartItems();
        adapter = new KeranjangAdapter(requireContext(), keranjangList, this::updateCartItem);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<KeranjangModel> loadCartItems() {
        SharedPreferences preferences = requireContext().getSharedPreferences("Keranjang", Context.MODE_PRIVATE);
        String cartJson = preferences.getString("produk_keranjang", "[]");

        Gson gson = new Gson();
        KeranjangModel[] keranjangArray = gson.fromJson(cartJson, KeranjangModel[].class);

        return new ArrayList<>(Arrays.asList(keranjangArray));
    }

    public void updateCartItem(KeranjangModel item) {
        // Memastikan item tidak null
        if (item != null) {
            // Lakukan pembaruan data sesuai dengan item yang diberikan
            for (int i = 0; i < keranjangList.size(); i++) {
                KeranjangModel currentItem = keranjangList.get(i);
                if (currentItem != null && currentItem.getNamaProduk().equals(item.getNamaProduk())) {
                    // Pembaruan data sesuai dengan item yang diterima
                    keranjangList.set(i, item); // Perbarui item di list

                    // Panggil notifyItemChanged melalui adapter
                    adapter.notifyItemChanged(i); // Notifikasi bahwa item telah diperbarui
                    break;
                }
            }
        } else {
            // Menangani kasus jika item null
            Log.e("KeranjangFragment", "Received null item for updateCartItem.");
        }

        // Simpan perubahan ke SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Keranjang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString("produk_keranjang", gson.toJson(keranjangList));
        editor.apply();

        // Perbarui adapter
        adapter.notifyDataSetChanged();
    }

}
