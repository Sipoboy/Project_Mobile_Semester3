package com.nyok.bottom_navigation.menu_dalam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set default fragment
        replaceFragment(new HomeFragment());

        // Listener untuk BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.Produk) {
                replaceFragment(new ProdukFragment());
            } else if (item.getItemId() == R.id.Keranjang) {
                replaceFragment(new KeranjangFragment());
            } else if (item.getItemId() == R.id.History) {
                replaceFragment(new SupportFragment());
            } else if (item.getItemId() == R.id.Setting) {
                replaceFragment(new SettingFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
        // Debugging log
        System.out.println("Fragment replaced with: " + fragment.getClass().getSimpleName());
    }

}

