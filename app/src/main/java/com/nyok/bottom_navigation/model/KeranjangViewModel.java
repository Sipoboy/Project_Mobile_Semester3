package com.nyok.bottom_navigation.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class KeranjangViewModel extends ViewModel {

    private MutableLiveData<List<Produk>> keranjangList;

    public KeranjangViewModel() {
        keranjangList = new MutableLiveData<>();
        keranjangList.setValue(new ArrayList<>());
    }

    // Mengembalikan LiveData agar dapat di-observe oleh UI
    public LiveData<List<Produk>> getKeranjangList() {
        return keranjangList;
    }

    // Menambahkan produk ke keranjang
    public void addProduk(Produk produk) {
        List<Produk> currentList = keranjangList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        List<Produk> updatedList = new ArrayList<>(currentList);
        updatedList.add(produk);
        keranjangList.setValue(updatedList);

        Log.d("KeranjangViewModel", "Produk ditambahkan: " + produk.getNamaProduk());
    }

    // Menghapus produk dari keranjang
    public void removeProduk(Produk produk) {
        List<Produk> currentList = keranjangList.getValue();
        if (currentList != null && currentList.contains(produk)) {
            List<Produk> updatedList = new ArrayList<>(currentList);
            updatedList.remove(produk);
            keranjangList.setValue(updatedList);

            Log.d("KeranjangViewModel", "Produk dihapus: " + produk.getNamaProduk());
        }
    }

    // Mengupdate daftar produk keranjang dengan daftar produk yang baru
    public void updateProdukList(List<Produk> produkList) {
        keranjangList.setValue(new ArrayList<>(produkList));
        Log.d("KeranjangViewModel", "List produk diperbarui, jumlah produk: " + produkList.size());
    }
}
