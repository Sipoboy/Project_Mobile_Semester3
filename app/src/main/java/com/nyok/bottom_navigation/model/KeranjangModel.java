package com.nyok.bottom_navigation.model;

public class KeranjangModel {
    private String namaProduk;
    private String kategori;
    private String harga;
    private String stok;
    private String gambar;

    public KeranjangModel(String namaProduk, String kategori, String harga, String stok, String gambar) {
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.gambar = gambar;
    }

    // Getter dan Setter
    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public String getHarga() { return harga; }
    public void setHarga(String harga) { this.harga = harga; }
    public String getStok() { return stok; }
    public void setStok(String stok) { this.stok = stok; }
    public String getGambar() { return gambar; }
    public void setGambar(String gambar) { this.gambar = gambar; }
}

