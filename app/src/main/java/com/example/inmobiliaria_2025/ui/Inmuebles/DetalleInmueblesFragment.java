package com.example.inmobiliaria_2025.ui.Inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentDetalleInmueblesBinding;
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;


public class DetalleInmueblesFragment extends Fragment {

    private DetalleInmueblesViewModel vm;
    private FragmentDetalleInmueblesBinding binding;
    public static DetalleInmueblesFragment newInstance() {
        return new DetalleInmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInmueblesBinding.bind(getLayoutInflater().inflate(R.layout.fragment_detalle_inmuebles, container, false));
         vm = new ViewModelProvider(this).get(DetalleInmueblesViewModel.class);




        vm.getInmuebles().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvIdInmueble.setText(inmueble.getIdInmueble() + "");
            binding.tvDireccionI.setText(inmueble.getDireccion());
            binding.tvUsoI.setText(inmueble.getUso());
            binding.tvAmbientesI.setText(inmueble.getAmbientes() + "");
            binding.tvLatitudI.setText(inmueble.getLatitud() + "");
            binding.tvLongitudI.setText(inmueble.getLongitud() + "");
            binding.tvValorI.setText(inmueble.getValor() + "");
            Glide.with(this)
                    .load(ApiClient.URLBASE + inmueble.getImagen())
                    .placeholder(R.drawable.inmuebles)
                    .error("null")
                    .into(binding.imgInmuebleD);
            binding.checkDisponible.setChecked(inmueble.getDisponible());
        });


         vm.ObtenerDetallesInmuebles(getArguments());

         binding.checkDisponible.setOnClickListener(view -> {

             vm.actualizarInmueble(binding.checkDisponible.isChecked());

         });
        return binding.getRoot();

    }


}
