package com.nyok.bottom_navigation.menu_dalam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nyok.bottom_navigation.databinding.FragmentSettingBinding;
import com.nyok.bottom_navigation.login.Login;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "myPref";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Menggunakan View Binding
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Ambil username dari SharedPreferences dan tampilkan
        String username = sharedPreferences.getString("username", "Guest");
        Log.d("SettingFragment", "Username yang diambil: " + username);
        binding.username.setText(username);

        // Atur fungsi klik untuk Edit Profile
        binding.linearLayoutEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), EditProfilActivity.class);
            startActivity(intent);
        });

        // Atur fungsi klik untuk Logout
        binding.linearLayoutLogout.setOnClickListener(v -> handleLogout());

        // Konfigurasi Switch notifikasi
        boolean isNotificationEnabled = sharedPreferences.getBoolean("notifications_enabled", false);
        binding.switchNotifications.setChecked(isNotificationEnabled);

        binding.switchNotifications.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            Toast.makeText(requireContext(), isChecked ? "Notifikasi diaktifkan" : "Notifikasi dimatikan", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notifications_enabled", isChecked);
            editor.apply();
        });

        // Atur fungsi klik untuk Change Password
        binding.linearLayoutChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PasswordActivity.class);
            startActivity(intent);
        });

        return view;
    }

    // Fungsi untuk menangani proses logout
    private void handleLogout() {
        try {
            // Update SharedPreferences untuk menghapus status login
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Menghapus semua data SharedPreferences
            editor.apply();

            Toast.makeText(requireContext(), "Berhasil keluar", Toast.LENGTH_SHORT).show();

            // Berpindah ke halaman login
            Intent logoutIntent = new Intent(requireContext(), Login.class);
            startActivity(logoutIntent);

            // Menutup aktivitas utama
            requireActivity().finish();
        } catch (Exception e) {
            Log.e("SettingFragment", "Error during logout: " + e.getMessage(), e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Bersihkan binding untuk mencegah kebocoran memori
    }
}
