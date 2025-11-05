package com.example.inmobiliaria_2025.ui.Contratos;

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

import com.example.inmobiliaria_2025.databinding.FragmentContratosBinding;
import com.example.inmobiliaria_2025.modelos.Inmuebles;


import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel vm;
    private FragmentContratosBinding binding;
    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= FragmentContratosBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(ContratosViewModel.class);
        vm.getListaContratosVigentes().observe(getViewLifecycleOwner(), new Observer<List<Inmuebles>>() {
            @Override
            public void onChanged(List<Inmuebles> inmuebles) {
                ContratosAdapter adapter = new ContratosAdapter(inmuebles,getContext(),getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(getContext(),1);//cantidad de card view en la vista
                RecyclerView rv = binding.rvListaContratos;
                rv.setLayoutManager(glm);
                rv.setAdapter(adapter);
            }
        });

       vm.getMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
           @Override
           public void onChanged(String s) {
               binding.tvContratos.setText(s);
           }
       });
        vm.LeerInmueblesPorContratos();
        return  binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

