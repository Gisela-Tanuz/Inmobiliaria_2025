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
      vm= new ViewModelProvider(this).get(DetalleInmueblesViewModel.class);
        binding = FragmentDetalleInmueblesBinding.inflate(inflater, container, false);

        vm.getInmuebles().observe(getViewLifecycleOwner(), this::onChanged);
         vm.ObtenerDetallesInmuebles(getArguments());

         binding.checkDisponible.setOnClickListener(view -> {

             vm.actualizarInmueble(binding.checkDisponible.isChecked());

         });
        return binding.getRoot();



    }


    private void onChanged(Inmuebles inmuebles) {

        binding.tvIdInmueble.setText(inmuebles.getIdInmueble() + "");
        binding.tvDireccionI.setText(inmuebles.getDireccion() );
        binding.tvUsoI.setText(inmuebles.getUso() );
        binding.tvAmbientesI.setText(inmuebles.getAmbientes() + "");
        binding.tvLatitudI.setText(inmuebles.getLatitud() + "");
        binding.tvInfoLongitud.setText(inmuebles.getLongitud() + "");
        binding.tvValorI.setText(inmuebles.getValor() + "");
        Glide.with(
                this)
                .load(ApiClient.URLBASE + inmuebles.getImagen())
                .placeholder(R.drawable.inmuebles)
                .error("null")
                .into(binding.imgInmuebleD);
    }
}
