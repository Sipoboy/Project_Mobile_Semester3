package com.nyok.bottom_navigation.menu_dalam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.adapter.KeranjangAdapter;
import com.nyok.bottom_navigation.model.KeranjangModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class KeranjangFragment extends Fragment {
    private RecyclerView recyclerView;
    private KeranjangAdapter adapter;
    private List<KeranjangModel> keranjangList;
    private TextView totalPriceText;
    private TextView subtotalPriceTxt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);

        recyclerView = view.findViewById(R.id.viewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        totalPriceText = view.findViewById(R.id.totalharga);
        subtotalPriceTxt = view.findViewById(R.id.subtotalharga);
        // Load data keranjang
        keranjangList = loadCartItems();
        adapter = new KeranjangAdapter(requireContext(), keranjangList, this::updateCartItem, this::removeCartItem);
        recyclerView.setAdapter(adapter);

        updateTotalPrice();

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
        // Update item sesuai dengan data baru
        for (int i = 0; i < keranjangList.size(); i++) {
            if (keranjangList.get(i).getNamaProduk().equals(item.getNamaProduk())) {
                keranjangList.set(i, item);
                adapter.notifyItemChanged(i); // Notifikasi perubahan pada item
                break;
            }
        }
        saveCartItems();
        updateTotalPrice();
    }

    public void removeCartItem(KeranjangModel item) {
        keranjangList.remove(item);
        adapter.notifyDataSetChanged(); // Notifikasi bahwa data telah berubah
        saveCartItems();
        updateTotalPrice();
    }

    private void saveCartItems() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Keranjang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString("produk_keranjang", gson.toJson(keranjangList));
        editor.apply();
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (KeranjangModel item : keranjangList) {
            totalPrice += item.getHarga() * item.getJumlah();
        }

        // Format totalPrice dengan NumberFormat untuk menampilkan dalam format Rupiah
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedTotalPrice = format.format(totalPrice);

        totalPriceText.setText(formattedTotalPrice);
        subtotalPriceTxt.setText(formattedTotalPrice);// Update tampilan total harga
    }
}
