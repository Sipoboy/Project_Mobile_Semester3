package com.nyok.bottom_navigation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyok.bottom_navigation.R
import com.nyok.bottom_navigation.model.Rekomendasi
import com.squareup.picasso.Picasso
import java.util.Locale

class RecomendationAdapter(
    private val context: Context,
    private var rekomendasiList: MutableList<Rekomendasi>
) : RecyclerView.Adapter<RecomendationAdapter.RekomendasiViewHolder>(), android.widget.Filterable {

    private val rekomendasiListFull: MutableList<Rekomendasi> = ArrayList(rekomendasiList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekomendasiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_holderrecomendation, parent, false)
        return RekomendasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: RekomendasiViewHolder, position: Int) {
        val rekomendasi = rekomendasiList[position]
        Log.d("RekomendasiAdapter", "Binding data: $rekomendasi")

        holder.namaProduk.text = rekomendasi.namaProduk
        holder.kategori.text = rekomendasi.namaKategori
        holder.harga.text = "Rp ${rekomendasi.harga}"
        holder.stok.text = "Stok: ${rekomendasi.stok}"

        // Gunakan Picasso untuk memuat gambar dari URL
        Picasso.get()
            .load(rekomendasi.gambar)
            .placeholder(R.drawable.grey_background)  // Placeholder ketika gambar sedang dimuat
            .error(R.drawable.grey_background)       // Gambar error jika gagal memuat
            .into(holder.gambar)
    }

    override fun getItemCount(): Int {
        return rekomendasiList.size
    }

    // Menambahkan implementasi Filterable
    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<Rekomendasi>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(rekomendasiListFull)
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()
                    for (item in rekomendasiListFull) {
                        // Filter berdasarkan nama produk atau kategori
                        if (item.namaProduk.lowercase(Locale.getDefault()).contains(filterPattern) ||
                            item.namaKategori.lowercase(Locale.getDefault()).contains(filterPattern)
                        ) {
                            filteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // Mengupdate data yang difilter
                rekomendasiList.clear()  // Kosongkan list sebelumnya
                if (results?.values != null) {
                    rekomendasiList.addAll(results.values as List<Rekomendasi>)  // Menambah hasil filter
                }
                notifyDataSetChanged() // Menyegarkan RecyclerView setelah filter
            }
        }
    }

    inner class RekomendasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaProduk: TextView = itemView.findViewById(R.id.tittleTxt)
        val kategori: TextView = itemView.findViewById(R.id.categoryTxt)
        val harga: TextView = itemView.findViewById(R.id.priceTxt)
        val stok: TextView = itemView.findViewById(R.id.stockTxt)
        val gambar: ImageView = itemView.findViewById(R.id.pic)
    }
}
