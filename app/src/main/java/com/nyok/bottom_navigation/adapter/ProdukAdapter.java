package com.nyok.bottom_navigation.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.model.Produk;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private List<Produk> produkList;
    private final OnProdukClickListener listener;
    private Context context;

    // Konstruktor
    public ProdukAdapter(Context context, List<Produk> produkList, OnProdukClickListener listener) {
        this.context = context;
        this.produkList = produkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {
        Produk produk = produkList.get(position);

        holder.namaProduk.setText(produk.getNamaProduk());
        holder.kategoriProduk.setText(produk.getKategori());
        holder.hargaProduk.setText("Rp " + produk.getHarga());
        holder.stokProduk.setText("Stok: " + produk.getStok());

        // Memuat gambar produk
        if (produk.getGambarUrl() != null && !produk.getGambarUrl().isEmpty()) {
            Picasso.get()
                    .load(produk.getGambarUrl())
                    .placeholder(R.drawable.cat1)
                    .error(R.drawable.cat1)
                    .into(holder.imgProduk);
        } else {
            holder.imgProduk.setImageResource(R.drawable.cat1);
        }

        // Klik pada item untuk memanggil listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProdukClicked(produk);
            }
        });

        // Klik pada tombol "Add to Cart" untuk menambahkan produk ke keranjang
        holder.itemView.findViewById(R.id.imgKeranjang).setOnClickListener(v -> {
            addItemToCart(produk);
            Toast.makeText(context, produk.getNamaProduk() + " telah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return produkList != null ? produkList.size() : 0;
    }

    // ViewHolder untuk item produk
    public static class ProdukViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduk;
        TextView namaProduk, kategoriProduk, hargaProduk, stokProduk;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.imgProduk);
            namaProduk = itemView.findViewById(R.id.txtNamaProduk);
            kategoriProduk = itemView.findViewById(R.id.txtKategoriProduk);
            hargaProduk = itemView.findViewById(R.id.txtHargaProduk);
            stokProduk = itemView.findViewById(R.id.txtStokProduk);
        }
    }

    // Interface untuk listener
    public interface OnProdukClickListener {
        void onProdukClicked(Produk produk);
    }

    // Fungsi untuk menambahkan produk ke keranjang (SharedPreferences)
    private void addItemToCart(Produk produk) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CartPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ambil data keranjang yang sudah ada
        String cartJson = sharedPreferences.getString("cart_items", "[]");
        Gson gson = new Gson();

        // Konversi string JSON menjadi List<Produk>
        Type type = new TypeToken<List<Produk>>(){}.getType();
        List<Produk> cartItems = gson.fromJson(cartJson, type);

        // Jika keranjang masih kosong, buat list baru
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Tambahkan produk baru ke dalam keranjang
        cartItems.add(produk);

        // Simpan kembali ke SharedPreferences
        String updatedCartJson = gson.toJson(cartItems);
        editor.putString("cart_items", updatedCartJson);
        editor.apply();
    }
}
