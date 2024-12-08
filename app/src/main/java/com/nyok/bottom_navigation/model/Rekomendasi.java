package com.nyok.bottom_navigation.model;

import com.google.gson.annotations.SerializedName;

public class Rekomendasi {
    @SerializedName("id_produk")
    private int idProduk;

    @SerializedName("nama_produk")
    private String namaProduk;

    @SerializedName("harga")
    private String harga;

    @SerializedName("stok")
    private int stok;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("nama_kategori")
    private String namaKategori;

    // Getter dan Setter
    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
