package com.nyok.bottom_navigation.menu_dalam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nyok.bottom_navigation.database.DatabaseHelperLogin;
import com.nyok.bottom_navigation.databinding.FragmentSettingBinding;
import com.nyok.bottom_navigation.login.Login;

public class SettingFragment extends Fragment {

    private @NonNull FragmentSettingBinding binding;
    private DatabaseHelperLogin db;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SettingFragment", "onCreateView called");

        // Menggunakan ViewBinding
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inisialisasi DatabaseHelperLogin
        db = new DatabaseHelperLogin(getActivity()); // Pastikan ini sudah diinisialisasi

        // Inisialisasi SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE);

        // Tombol logout
        TextView btnkeluar = binding.textLogout;

        btnkeluar.setOnClickListener(v -> {
            // Memperbarui status sesi
            Boolean updateSession = db.upgradeSession("Kosong", 1);
            if (updateSession) {
                Toast.makeText(getActivity().getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();

                // Update SharedPreferences untuk status login
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("masuk", false);
                editor.apply();

                // Berpindah ke halaman login
                Intent keluar = new Intent(getActivity(), Login.class);
                startActivity(keluar);
                getActivity().finish(); // Menutup aktivitas yang sedang berjalan
            }
        });

        return view;
    }
}
