package com.nyok.bottom_navigation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.model.KeranjangModel;

import java.util.List;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.ViewHolder> {
    private Context context;
    private List<KeranjangModel> keranjangList;
    private OnCartItemChangeListener listener;

    public interface OnCartItemChangeListener {
        void onCartItemChanged(KeranjangModel item);
    }

    public KeranjangAdapter(Context context, List<KeranjangModel> keranjangList, OnCartItemChangeListener listener) {
        this.context = context;
        this.keranjangList = keranjangList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KeranjangModel item = keranjangList.get(position);

        if (item != null) {
            // Memuat gambar produk dari URL menggunakan Glide
            Glide.with(context)
                    .load(item.getGambar()) // Menggunakan URL gambar
                    .into(holder.pic);

            holder.namaProduk.setText(item.getNamaProduk());
            holder.harga.setText("Rp. " + item.getHarga());
            holder.jumlah.setText(String.valueOf(item.getJumlah()));

            double totalPerProduk = item.getHarga() * item.getJumlah();
            holder.totalPerProduk.setText(formatCurrency(totalPerProduk));

            // Mengatur klik untuk menambah jumlah
            holder.tambah.setOnClickListener(v -> {
                item.setJumlah(item.getJumlah() + 1);
                listener.onCartItemChanged(item);
                double totalPerProdukUpdated = item.getHarga() * item.getJumlah();
                holder.totalPerProduk.setText(formatCurrency(totalPerProdukUpdated));
            });

            // Mengatur klik untuk mengurangi jumlah
            holder.kurang.setOnClickListener(v -> {
                if (item.getJumlah() > 1) {
                    item.setJumlah(item.getJumlah() - 1);
                    listener.onCartItemChanged(item);
                    double totalPerProdukUpdated = item.getHarga() * item.getJumlah();
                    holder.totalPerProduk.setText(formatCurrency(totalPerProdukUpdated));
                } else if (item.getJumlah() == 1) {
                    keranjangList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, keranjangList.size());
                    listener.onCartItemChanged(null);
                }
            });
        }
    }


    // Fungsi untuk memformat angka menjadi format mata uang
    private String formatCurrency(double amount) {
        // Format angka dengan dua angka desimal (jika diperlukan)
        return String.format("Rp. %, .2f", amount); // Menggunakan .2f untuk dua angka desimal
    }

    @Override
    public int getItemCount() {
        return keranjangList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaProduk, harga, jumlah, totalPerProduk; // Menambahkan totalPerProduk
        ImageView tambah, kurang, pic; // Menambahkan ImageView pic

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaProduk = itemView.findViewById(R.id.titleTxt);
            harga = itemView.findViewById(R.id.HargaPerItem);
            jumlah = itemView.findViewById(R.id.numberitemTxt);
            totalPerProduk = itemView.findViewById(R.id.totalperproduk); // Inisialisasi TextView totalperproduk
            tambah = itemView.findViewById(R.id.pluscartbtn);
            kurang = itemView.findViewById(R.id.minuscartBtn);
            pic = itemView.findViewById(R.id.pic); // Inisialisasi ImageView pic
        }
    }

}


