package com.nyok.bottom_navigation.menu_dalam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.databinding.ActivityMainBinding;
import com.nyok.bottom_navigation.model.KeranjangModel;
import com.nyok.bottom_navigation.model.Produk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Menampilkan HomeFragment saat pertama kali membuka aplikasi
        replaceFragment(new HomeFragment());

        // Listener untuk BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.Produk) {
                replaceFragment(new ProdukFragment());
            } else if (item.getItemId() == R.id.Keranjang) {
                // Memuat item keranjang sebelum menampilkan KeranjangFragment
                loadCartItems();
                replaceFragment(new KeranjangFragment());
            } else if (item.getItemId() == R.id.History) {
                replaceFragment(new SupportFragment());
            } else if (item.getItemId() == R.id.Setting) {
                replaceFragment(new SettingFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        // Mengganti fragment yang aktif
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

        // Log untuk debugging
        Log.d("MainActivity", "Fragment replaced with: " + fragment.getClass().getSimpleName());
    }

    private void loadCartItems() {
        // Memeriksa apakah ada produk di keranjang
        SharedPreferences preferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        String cartJson = preferences.getString("cart_items", "[]");

        if (cartJson.equals("[]")) {
            // Jika keranjang kosong, beri tahu pengguna
            Log.d("MainActivity", "Keranjang kosong.");
        } else {
            // Menampilkan produk di keranjang (bisa dilakukan dengan menampilkan dialog atau log)
            Log.d("MainActivity", "Produk di keranjang: " + cartJson);
        }
    }

    public void addItemToCart(Produk produk) {
        SharedPreferences sharedPreferences = getSharedPreferences("Keranjang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ambil keranjang saat ini
        Gson gson = new Gson();
        String cartJson = sharedPreferences.getString("produk_keranjang", "[]");
        KeranjangModel[] keranjangArray = gson.fromJson(cartJson, KeranjangModel[].class);

        List<KeranjangModel> keranjangList = new ArrayList<>(Arrays.asList(keranjangArray));

        // Periksa apakah produk sudah ada di keranjang
        boolean isUpdated = false;
        for (KeranjangModel item : keranjangList) {
            if (item.getNamaProduk().equals(produk.getNamaProduk())) {
                item.setJumlah(item.getJumlah() + 1); // Tambah jumlah
                isUpdated = true;
                break;
            }
        }

        // Jika belum ada, tambahkan produk baru
        if (!isUpdated) {
            KeranjangModel newItem = new KeranjangModel(
                    produk.getNamaProduk(),
                    produk.getKategori(),
                    produk.getHarga(),
                    produk.getStok(),
                    produk.getGambarUrl(),
                    1 // Jumlah default 1
            );
            keranjangList.add(newItem);
        }

        // Simpan kembali ke SharedPreferences
        editor.putString("produk_keranjang", gson.toJson(keranjangList));
        editor.apply();
    }

}
