package com.nyok.bottom_navigation.model;

import com.google.gson.annotations.SerializedName;

public class Produk {
    @SerializedName("ProdukId")
    private int produkId;

    @SerializedName("NamaProduk")
    private String namaProduk;

    @SerializedName("Kategori")
    private String kategori;

    @SerializedName("Ukuran")
    private String ukuran;

    @SerializedName("Harga")
    private Integer harga; // Menggunakan Integer untuk nullable

    @SerializedName("Stok")
    private Integer stok; // Menggunakan Integer untuk nullable

    @SerializedName("GambarUrl")
    private String gambarUrl;

    @SerializedName("DibuatPada")
    private String dibuatPada;

    // Constructor
    public Produk(int produkId, String namaProduk, String kategori, String ukuran,
                  Integer harga, Integer stok, String gambarUrl, String dibuatPada) {
        this.produkId = produkId;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.ukuran = ukuran;
        this.harga = harga;
        this.stok = stok;
        this.gambarUrl = gambarUrl;
        this.dibuatPada = dibuatPada;
    }

    // Getter dan Setter
    public int getProdukId() {
        return produkId;
    }

    public void setProdukId(int produkId) {
        this.produkId = produkId;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public String getDibuatPada() {
        return dibuatPada;
    }

    public void setDibuatPada(String dibuatPada) {
        this.dibuatPada = dibuatPada;
    }

    @Override
    public String toString() {
        return "Produk{" +
                "produkId=" + produkId +
                ", namaProduk='" + namaProduk + '\'' +
                ", kategori='" + kategori + '\'' +
                ", ukuran='" + ukuran + '\'' +
                ", harga=" + harga +
                ", stok=" + stok +
                ", gambarUrl='" + gambarUrl + '\'' +
                ", dibuatPada='" + dibuatPada + '\'' +
                '}';
    }
}
