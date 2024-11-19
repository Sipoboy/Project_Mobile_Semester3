package com.nyok.bottom_navigation.menu_dalam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.databinding.FragmentProfilBinding;
import java.util.Calendar;
import static android.app.Activity.RESULT_OK;

public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private static final int PICK_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Mengembalikan root view dari binding
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setVariable();
    }

    private void setVariable() {
        binding.btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        // Menangani klik pada imgEditIcon untuk memilih gambar dari galeri
        binding.imgEditIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);
        });

        // Menangani klik pada tvTanggal untuk memilih tanggal
        binding.tvTanggal.setOnClickListener(v -> showDatePicker());

        binding.method1.setOnClickListener(v -> selectMethod1());
        binding.method2.setOnClickListener(v -> selectMethod2());

        // Inisialisasi metode yang dipilih secara default
        selectMethod1();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                binding.imgProfilePicture.setImageURI(imageUri);
            }
        }
    }

    private void showDatePicker() {
        // Mendapatkan tanggal saat ini sebagai default
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Membuat DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Mengatur tanggal yang dipilih pada TextView tvTanggal
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    binding.tvTanggal.setText(selectedDate);
                },
                year, month, day
        );

        // Menampilkan DatePickerDialog
        datePickerDialog.show();
    }

    private void selectMethod1() {
        updateMethod1Selected();
        updateMethod2Unselected();
    }

    private void selectMethod2() {
        updateMethod1Unselected();
        updateMethod2Selected();
    }

    private void updateMethod1Selected() {
        binding.method1.setBackgroundResource(R.drawable.custom_btn_blue);
        binding.method1Title1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
    }

    private void updateMethod1Unselected() {
        binding.method1.setBackgroundResource(R.drawable.grey_background);
        binding.method1Title1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
    }

    private void updateMethod2Selected() {
        binding.method2.setBackgroundResource(R.drawable.custom_btn_blue);
        binding.method2Title2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
    }

    private void updateMethod2Unselected() {
        binding.method2.setBackgroundResource(R.drawable.grey_background);
        binding.method2Title2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
    }
}
