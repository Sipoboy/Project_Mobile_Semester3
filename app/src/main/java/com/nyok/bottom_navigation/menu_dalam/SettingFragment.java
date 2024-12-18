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
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.nyok.bottom_navigation.databinding.FragmentSettingBinding;
import com.nyok.bottom_navigation.login.Login;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding; // View Binding untuk layout fragment_setting
    private SharedPreferences sharedPreferences; // SharedPreferences untuk menyimpan data sesi
    private static final String SHARED_PREF_NAME = "myPref"; // Nama SharedPreferences yang digunakan

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Menggunakan View Binding untuk menghubungkan layout
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        try {
            // Ambil SharedPreferences
            sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

            // Ambil username dari SharedPreferences
            String username = sharedPreferences.getString("username", "Guest");
            Log.d("SettingFragment", "Username yang diambil: " + username);

            // Tampilkan username di TextView
            binding.username.setText(username);

            // Inisialisasi DatabaseHelperLogin
            LinearLayout editProfileLayout = binding.linearLayoutEditProfile;
            if (editProfileLayout != null) {
                editProfileLayout.setOnClickListener(v -> {
                    Intent intent = new Intent(requireContext(), EditProfilActivity.class);
                    startActivity(intent);
                });
            } else {
                Log.e("SettingFragment", "Edit Profile layout is null. Check XML ID.");
            }



            // Konfigurasi tombol logout
            LinearLayout logoutLayout = binding.linearLayoutLogout;
            if (logoutLayout != null) {
                logoutLayout.setOnClickListener(v -> handleLogout());
            } else {
                Log.e("SettingFragment", "Logout layout is null. Check XML ID.");
            }

            // Konfigurasi Switch notifikasi
            Switch notificationSwitch = binding.switchNotifications;
            if (notificationSwitch != null) {
                // Ambil status notifikasi dari SharedPreferencesa
                boolean isNotificationEnabled = sharedPreferences.getBoolean("notifications_enabled", false);
                notificationSwitch.setChecked(isNotificationEnabled);

                // Listener untuk perubahan status Switch
                notificationSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(requireContext(), "Notifikasi diaktifkan", Toast.LENGTH_SHORT).show();
                        Log.d("SettingFragment", "Notification enabled.");
                    } else {
                        Toast.makeText(requireContext(), "Notifikasi dimatikan", Toast.LENGTH_SHORT).show();
                        Log.d("SettingFragment", "Notification disabled.");
                    }

                    // Simpan status notifikasi di SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("notifications_enabled", isChecked);
                    editor.apply();
                });
            } else {
                Log.e("SettingFragment", "Notification switch is null. Check XML ID.");
            }
            LinearLayout changePasswordLayout = binding.linearLayoutChangePassword;
            if (changePasswordLayout != null) {
                changePasswordLayout.setOnClickListener(v -> {
                    Intent intent = new Intent(requireContext(), PasswordActivity.class);
                    startActivity(intent);
                });
            } else {
                Log.e("SettingFragment", "Change Password layout is null. Check XML ID.");
            }


        } catch (Exception e) {
            Log.e("SettingFragment", "Error during onCreateView initialization: " + e.getMessage(), e);
        }

        return view;
    }

//     Fungsi untuk menangani proses logout
    private void handleLogout() {
        try {
        // Menampilkan pesan berhasil logout
            Toast.makeText(requireContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();

        // Update SharedPreferences untuk status login
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("masuk"); // Menghapus status login
            editor.apply();

        // Berpindah ke halaman login
            Intent logoutIntent = new Intent(getContext(), Login.class);
            startActivity(logoutIntent);

        // Menutup aktivitas saat ini
            requireActivity().finish();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Terjadi kesalahan saat logout.", Toast.LENGTH_SHORT).show();
            Log.e("SettingFragment", "Error during logout: " + e.getMessage(), e);
    }
}



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Bersihkan binding untuk mencegah kebocoran memori
        binding = null;
    }
}
