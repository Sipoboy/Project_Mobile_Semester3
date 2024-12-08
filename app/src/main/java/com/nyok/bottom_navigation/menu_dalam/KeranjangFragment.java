package com.nyok.bottom_navigation.menu_dalam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.adapter.ProdukAdapter;
import com.nyok.bottom_navigation.model.Produk;
import com.nyok.bottom_navigation.model.KeranjangViewModel;

import java.util.List;

public class KeranjangFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private ProdukAdapter adapter;
//    private KeranjangViewModel keranjangViewModel;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);
//
//        recyclerView = view.findViewById(R.id.viewCart);
//        View txtKeranjangKosong = view.findViewById(R.id.emptyTxt);
//
//        keranjangViewModel = new ViewModelProvider(requireActivity()).get(KeranjangViewModel.class);
//
//        keranjangViewModel.getKeranjangList().observe(getViewLifecycleOwner(), produkList -> {
//            updateRecyclerView(produkList, txtKeranjangKosong);
//        });
//
//        return view;
//    }
//
//    private void updateRecyclerView(List<Produk> produkList, View txtKeranjangKosong) {
//        if (adapter == null) {
//            adapter = new ProdukAdapter(produkList, getContext(), KeranjangViewModel);
//            recyclerView.setAdapter(adapter);
//        } else {
//            adapter.updateProdukList(produkList);
//        }
//
//        if (produkList.isEmpty()) {
//            recyclerView.setVisibility(View.GONE);
//            txtKeranjangKosong.setVisibility(View.VISIBLE);
//        } else {
//            recyclerView.setVisibility(View.VISIBLE);
//            txtKeranjangKosong.setVisibility(View.GONE);
//        }
//    }
}
