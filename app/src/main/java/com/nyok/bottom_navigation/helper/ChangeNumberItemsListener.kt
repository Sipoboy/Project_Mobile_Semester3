package com.nyok.bottom_navigation.adapter

import com.nyok.bottom_navigation.model.Produk

interface ChangeNumberItemsListener {
    fun onProdukClicked(produk: Produk)
}
