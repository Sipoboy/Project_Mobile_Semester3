package com.nyok.bottom_navigation.menu_dalam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nyok.bottom_navigation.R;

public class EditProfilActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "myPref"; // Nama SharedPreferences yang digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        // Ambil SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Ambil data pengguna dari SharedPreferences
        String username = sharedPreferences.getString("username", "Guest");
        String role = sharedPreferences.getString("role", "User");
        String email = sharedPreferences.getString("email", "Tidak tersedia");
        String no_telp = sharedPreferences.getString("no_telp", "Tidak tersedia");
        String alamat = sharedPreferences.getString("alamat", "Tidak tersedia");

        // Log data untuk debugging
        Log.d("EditProfilActivity", "Username: " + username + ", Role: " + role +
                ", Email: " + email + ", No Telp: " + no_telp + ", Alamat: " + alamat);

        // Ambil referensi TextView dari layout
        TextView usernameTextView = findViewById(R.id.username);
        TextView roleTextView = findViewById(R.id.Role);
        TextView emailTextView = findViewById(R.id.email);
        TextView phoneTextView = findViewById(R.id.status);
        TextView addressTextView = findViewById(R.id.alamat);

        // Tampilkan data di TextView
        usernameTextView.setText(username);
        roleTextView.setText(role);
        emailTextView.setText(email);
        phoneTextView.setText(no_telp);
        addressTextView.setText(alamat);
    }
}
