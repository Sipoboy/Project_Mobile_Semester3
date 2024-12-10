package com.nyok.bottom_navigation.menu_dalam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.adapter.RecomendationAdapter;
import com.nyok.bottom_navigation.adapter.SlideAdapter;
import com.nyok.bottom_navigation.databinding.FragmentHomefragmenBinding;
import com.nyok.bottom_navigation.model.MainViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomefragmenBinding binding;
    public static final String SHARED_PREF_NAME = "myPref";
    private SharedPreferences sharedPreferences;

    private ViewPager2 viewPager2;
    private SlideAdapter slideAdapter;
    private ProgressBar progressBar, recomendationProgressBar, categoryProgressBar;
    private RecyclerView recomendationRecyclerView;
    private RecomendationAdapter recomendationAdapter;
    private EditText searchEditText; // Input pencarian
    private MainViewModel mainViewModel;  // ViewModel

    // Status pemuatan kategori
    private boolean isMakananLoaded = false;
    private boolean isMinumanLoaded = false;
    private boolean isViewAllLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomefragmenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewPager2 = binding.viewPager2;
        progressBar = binding.progressbarslider;
        recomendationProgressBar = binding.progressrecomendation;
        categoryProgressBar = binding.progressBarcategory;
        recomendationRecyclerView = binding.viewrecomendation;
        searchEditText = binding.searchEditText;

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Menampilkan username dari SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, getContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        binding.namauser.setText(username);

        // Memuat banner
        loadBanners();

        // Menampilkan rekomendasi produk
        loadRecommendations();

        // Menangani ikon notifikasi
        ImageView btnNotifikasi = binding.btnnotifikasi;
        btnNotifikasi.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Notification_Activity.class);
            startActivity(intent);
        });

        // Menangani ikon pencarian
        ImageView btnSearch = binding.btnsearch;
        btnSearch.setOnClickListener(v -> {
            if (searchEditText.getVisibility() == View.GONE) {
                searchEditText.setVisibility(View.VISIBLE);
                searchEditText.requestFocus();
            } else {
                searchEditText.setVisibility(View.GONE);
            }
        });

        // Filter pencarian
        setupSearchFilter();

        // Menangani klik pada ikon kategori Makanan
        ImageView imgMakanan = binding.imgMakanan;
        imgMakanan.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MakananActivity.class);
            startActivity(intent);
        });

// Menangani klik pada ikon kategori Minuman
        ImageView imgMinuman = binding.imgMinuman;
        imgMinuman.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MinumanActivity.class);
            startActivity(intent);
        });

// Menangani klik pada ikon kategori View All
        ImageView imgViewAll = binding.imgViewAll;
        imgViewAll.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProdukFragment.class);
            startActivity(intent);
        });


        return view;
    }

    // Fungsi untuk memuat banner
    private void loadBanners() {
        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.loadBanners();

        mainViewModel.getBanners().observe(getViewLifecycleOwner(), sliderModels -> {
            if (sliderModels != null && !sliderModels.isEmpty()) {
                slideAdapter = new SlideAdapter(sliderModels, viewPager2);
                viewPager2.setAdapter(slideAdapter);

                binding.dotIndicator.setVisibility(View.VISIBLE);
                binding.dotIndicator.setViewPager2(viewPager2);
            }

            new Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 1000);
        });
    }

    // Fungsi untuk memuat rekomendasi produk
    private void loadRecommendations() {
        recomendationProgressBar.setVisibility(View.VISIBLE);
        mainViewModel.loadRecommendations();

        mainViewModel.getRecommendations().observe(getViewLifecycleOwner(), rekomendasiList -> {
            if (rekomendasiList != null && !rekomendasiList.isEmpty()) {
                recomendationAdapter = new RecomendationAdapter(requireContext(), rekomendasiList);

                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
                recomendationRecyclerView.setLayoutManager(layoutManager);
                recomendationRecyclerView.setAdapter(recomendationAdapter);
            } else {
                Log.e("HomeFragment", "Data rekomendasi kosong.");
            }
            recomendationProgressBar.setVisibility(View.GONE);
        });
    }

    // Simulasi pemuatan kategori Makanan
    private void loadMakanan() {
        new Handler().postDelayed(() -> {
            isMakananLoaded = true;
            checkCategoryLoadStatus();
        }, 1000); // Simulasi delay 1 detik
    }

    // Simulasi pemuatan kategori Minuman
    private void loadMinuman() {
        new Handler().postDelayed(() -> {
            isMinumanLoaded = true;
            checkCategoryLoadStatus();
        }, 1000); // Simulasi delay 1 detik
    }

    // Simulasi pemuatan kategori View All
    private void loadViewAll() {
        new Handler().postDelayed(() -> {
            isViewAllLoaded = true;
            checkCategoryLoadStatus();
        }, 1000); // Simulasi delay 1 detik
    }

    // Cek status pemuatan kategori
    private void checkCategoryLoadStatus() {
        if (isMakananLoaded && isMinumanLoaded && isViewAllLoaded) {
            categoryProgressBar.setVisibility(View.GONE);
        }
    }

    // Mengatur filter pencarian
    private void setupSearchFilter() {
        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                if (recomendationAdapter != null) {
                    recomendationAdapter.getFilter().filter(s.toString());
                }
            }
        });
    }
}
