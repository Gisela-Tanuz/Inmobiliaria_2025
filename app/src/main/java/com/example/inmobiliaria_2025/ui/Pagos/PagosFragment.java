package com.example.inmobiliaria_2025.ui.Pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInquilinosBinding;
import com.example.inmobiliaria_2025.databinding.FragmentPagosBinding;
import com.example.inmobiliaria_2025.modelos.Pagos;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel vm;
    private FragmentPagosBinding binding;
    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PagosViewModel.class);
        binding= FragmentPagosBinding.inflate(inflater,container,false);
        vm.ObtenerPagos(getArguments());
     vm.getListaPagos().observe(getViewLifecycleOwner(), new Observer<List<Pagos>>() {
         @Override
         public void onChanged(List<Pagos> pagos) {
             PagosAdapter adapter = new PagosAdapter(pagos,getContext(),getLayoutInflater());
             GridLayoutManager glm = new GridLayoutManager(getContext(),1);
             RecyclerView rv = binding.rvListaPagos;
             rv.setLayoutManager(glm);
             rv.setAdapter(adapter);
         }
     });

 vm.getMensajePagos().observe(getViewLifecycleOwner(), new Observer<String>() {
     @Override
     public void onChanged(String s) {
         binding.tvMensajePagos.setText(s);
     }
 });
        return binding.getRoot();
    }



}