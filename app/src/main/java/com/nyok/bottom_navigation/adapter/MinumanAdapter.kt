package com.nyok.bottom_navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyok.bottom_navigation.model.Minuman
import kotlin.math.min

class MinumanAdapter(private val minumanList: List<Minuman>) : RecyclerView.Adapter<MinumanAdapter.MinumanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinumanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_minuman, parent, false)
        return MinumanViewHolder(view)
    }

    override fun onBindViewHolder(holder: MinumanViewHolder, position: Int) {
        val minuman = minumanList[position]
        holder.txtNamaProduk.text = minuman.nama_produk
        holder.txtKategoriProduk.text = minuman.nama_kategori
        holder.txtHargaProduk.text = "Harga: ${minuman.harga}"
        holder.txtStokProduk.text = "Stok: ${minuman.stok}"

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(holder.itemView.context)
            .load(minuman.gambar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imgProduk)
    }

    override fun getItemCount(): Int {
        return minumanList.size
    }

    // ViewHolder inner class
    inner class MinumanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNamaProduk: TextView = itemView.findViewById(R.id.txtNamaProduk)
        val txtKategoriProduk: TextView = itemView.findViewById(R.id.txtKategoriProduk)
        val txtHargaProduk: TextView = itemView.findViewById(R.id.txtHargaProduk)
        val txtStokProduk: TextView = itemView.findViewById(R.id.txtStokProduk)
        val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
    }
}
