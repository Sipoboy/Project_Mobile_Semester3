package com.nyok.bottom_navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.model.Produk;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private List<Produk> produkList; // Menggunakan model `Produk`

    // Konstruktor untuk menerima daftar produk
    public ProdukAdapter(List<Produk> produkList) {
        this.produkList = produkList;
    }

    // Membuat tampilan item untuk RecyclerView
    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(itemView);
    }

    // Menghubungkan data ke ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {
        Produk produk = produkList.get(position);

        // Menampilkan data pada ViewHolder
        holder.namaProduk.setText(produk.getNamaProduk());
        holder.kategoriProduk.setText(produk.getKategori());
        holder.hargaProduk.setText("Rp " + produk.getHarga());

        // Menggunakan Picasso untuk memuat gambar
        if (produk.getGambarUrl() != null && !produk.getGambarUrl().isEmpty()) {
            Picasso.get()
                    .load(produk.getGambarUrl())
                    .placeholder(R.drawable.cat1) // Gambar sementara saat loading
                    .error(R.drawable.cat1) // Gambar default jika URL gagal dimuat
                    .into(holder.imgProduk);
        } else {
            holder.imgProduk.setImageResource(R.drawable.cat1); // Gambar default jika URL kosong
        }
    }

    // Mengembalikan jumlah item dalam RecyclerView
    @Override
    public int getItemCount() {
        return produkList != null ? produkList.size() : 0;
    }

    // ViewHolder untuk menampung tampilan item
    public static class ProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView namaProduk, kategoriProduk, hargaProduk;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.imgProduk);
            namaProduk = itemView.findViewById(R.id.txtNamaProduk);
            kategoriProduk = itemView.findViewById(R.id.txtKategoriProduk);
            hargaProduk = itemView.findViewById(R.id.txtHargaProduk);
        }
    }

    // Menambahkan method untuk memperbarui data produk
    public void updateProdukList(List<Produk> newProdukList) {
        this.produkList = newProdukList;
        notifyDataSetChanged(); // Memberitahu RecyclerView untuk memperbarui data
    }
}
