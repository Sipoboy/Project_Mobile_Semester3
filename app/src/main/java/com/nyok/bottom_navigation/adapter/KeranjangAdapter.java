package com.nyok.bottom_navigation.adapter;

import android.content.Context;
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
    private OnCartItemRemoveListener removeListener;

    public interface OnCartItemChangeListener {
        void onCartItemChanged(KeranjangModel updatedItem); // Menyediakan item yang diperbarui
    }

    public interface OnCartItemRemoveListener {
        void onCartItemRemoved(KeranjangModel removedItem); // Menyediakan item yang dihapus
    }

    // Pastikan konstruktor menerima listener dengan benar
    public KeranjangAdapter(Context context, List<KeranjangModel> keranjangList,
                            OnCartItemChangeListener listener, OnCartItemRemoveListener removeListener) {
        this.context = context;
        this.keranjangList = keranjangList;
        this.listener = listener;
        this.removeListener = removeListener;
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
            Glide.with(context)
                    .load(item.getGambar()) // Menggunakan URL gambar
                    .into(holder.pic);

            holder.namaProduk.setText(item.getNamaProduk());
            holder.harga.setText("Rp. " + item.getHarga());
            holder.jumlah.setText(String.valueOf(item.getJumlah()));

            double totalPerProduk = item.getHarga() * item.getJumlah();
            holder.totalPerProduk.setText(formatCurrency(totalPerProduk));

            holder.tambah.setOnClickListener(v -> {
                item.setJumlah(item.getJumlah() + 1);
                listener.onCartItemChanged(item); // Kirim item yang diperbarui
                double totalPerProdukUpdated = item.getHarga() * item.getJumlah();
                holder.totalPerProduk.setText(formatCurrency(totalPerProdukUpdated));
            });

            holder.kurang.setOnClickListener(v -> {
                if (item.getJumlah() > 1) {
                    item.setJumlah(item.getJumlah() - 1);
                    listener.onCartItemChanged(item); // Kirim item yang diperbarui
                    double totalPerProdukUpdated = item.getHarga() * item.getJumlah();
                    holder.totalPerProduk.setText(formatCurrency(totalPerProdukUpdated));
                } else if (item.getJumlah() == 1) {
                    keranjangList.remove(position);
                    notifyItemRemoved(position);
                    removeListener.onCartItemRemoved(item); // Kirim item yang dihapus
                }
            });
        }
    }

    private String formatCurrency(double amount) {
        return String.format("Rp. %, .2f", amount); // Format angka sebagai mata uang
    }

    @Override
    public int getItemCount() {
        return keranjangList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaProduk, harga, jumlah, totalPerProduk;
        ImageView tambah, kurang, pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaProduk = itemView.findViewById(R.id.titleTxt);
            harga = itemView.findViewById(R.id.HargaPerItem);
            jumlah = itemView.findViewById(R.id.numberitemTxt);
            totalPerProduk = itemView.findViewById(R.id.totalperproduk);
            tambah = itemView.findViewById(R.id.pluscartbtn);
            kurang = itemView.findViewById(R.id.minuscartBtn);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
