package com.nyok.bottom_navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyok.bottom_navigation.model.Makanan

class MakananAdapter(private val makananList: List<Makanan>) : RecyclerView.Adapter<MakananAdapter.MakananViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakananViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
        return MakananViewHolder(view)
    }

    override fun onBindViewHolder(holder: MakananViewHolder, position: Int) {
        val makanan = makananList[position]
        holder.txtNamaProduk.text = makanan.nama_produk
        holder.txtKategoriProduk.text = makanan.nama_kategori
        holder.txtHargaProduk.text = "Harga: ${makanan.harga}"
        holder.txtStokProduk.text = "Stok: ${makanan.stok}"

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(holder.itemView.context)
            .load(makanan.gambar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imgProduk)
    }

    override fun getItemCount(): Int {
        return makananList.size
    }

    // ViewHolder inner class
    inner class MakananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNamaProduk: TextView = itemView.findViewById(R.id.txtNamaProduk)
        val txtKategoriProduk: TextView = itemView.findViewById(R.id.txtKategoriProduk)
        val txtHargaProduk: TextView = itemView.findViewById(R.id.txtHargaProduk)
        val txtStokProduk: TextView = itemView.findViewById(R.id.txtStokProduk)
        val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
    }
}
