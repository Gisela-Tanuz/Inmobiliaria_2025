package com.example.inmobiliaria_2025.ui.Inmuebles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.modelos.Inmuebles;

import java.util.List;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private  InmueblesViewModel vm;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);

       vm.getListaInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmuebles>>() {
           @Override
           public void onChanged(List<Inmuebles> inmuebles) {
               InmueblesAdapter adapter = new InmueblesAdapter(inmuebles,getContext(),getLayoutInflater());
               GridLayoutManager glm = new GridLayoutManager(getContext(),2);//cantidad de card view en la vista
               RecyclerView rv = binding.rvListaInmuebles;
               rv.setLayoutManager(glm);
               rv.setAdapter(adapter);
           }
       });

        binding.btnFlotAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.agregarInmueblesFragment);
            }
        });
        vm.leerInmuebles();
         return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}