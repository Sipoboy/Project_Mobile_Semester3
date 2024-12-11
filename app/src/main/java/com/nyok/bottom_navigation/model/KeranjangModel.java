package com.nyok.bottom_navigation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KeranjangModel implements Parcelable {
    private String namaProduk;
    private String kategori;
    private double harga;
    private int stok;
    private String gambar; // Mengubah tipe data menjadi String untuk URL gambar
    private int jumlah; // Menambahkan jumlah produk dalam keranjang

    // Konstruktor untuk menerima URL gambar
    public KeranjangModel(String namaProduk, String kategori, double harga, int stok, String gambar, int jumlah) {
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.gambar = gambar; // Menyimpan URL gambar
        this.jumlah = jumlah; // Set jumlah saat inisialisasi
    }

    // Konstruktor dari Parcel (untuk Parcelable)
    protected KeranjangModel(Parcel in) {
        namaProduk = in.readString();
        kategori = in.readString();
        harga = in.readDouble();
        stok = in.readInt();
        gambar = in.readString(); // Membaca URL gambar dari parcel
        jumlah = in.readInt(); // Membaca jumlah dari parcel
    }

    // Creator untuk Parcelable
    public static final Creator<KeranjangModel> CREATOR = new Creator<KeranjangModel>() {
        @Override
        public KeranjangModel createFromParcel(Parcel in) {
            return new KeranjangModel(in);
        }

        @Override
        public KeranjangModel[] newArray(int size) {
            return new KeranjangModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaProduk);
        dest.writeString(kategori);
        dest.writeDouble(harga);
        dest.writeInt(stok);
        dest.writeString(gambar); // Menulis URL gambar ke parcel
        dest.writeInt(jumlah); // Menulis jumlah ke parcel
    }

    // Getter dan Setter
    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public String getGambar() { return gambar; }  // Mengambil URL gambar
    public void setGambar(String gambar) { this.gambar = gambar; }  // Mengatur URL gambar

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    // Menghitung total harga berdasarkan jumlah produk
    public double getTotalHarga() {
        return harga * jumlah; // Harga per produk dikalikan dengan jumlah
    }
}
