package com.example.inmobiliaria_2025.ui.Contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentDetalleContratosBinding;
import com.example.inmobiliaria_2025.modelos.Contratos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetalleContratosFragment extends Fragment {

    private DetalleContratosViewModel vm;
    private FragmentDetalleContratosBinding binding;
    public static DetalleContratosFragment newInstance() {
        return new DetalleContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(DetalleContratosViewModel.class);

       binding = FragmentDetalleContratosBinding.bind(getLayoutInflater().inflate(R.layout.fragment_detalle_contratos, container, false));
        vm.ObtenerContrato(getArguments());
       vm.getContratos().observe(getViewLifecycleOwner(), new Observer<Contratos>() {
    @Override
    public void onChanged(Contratos contratos) {
        binding.tvIdContrato.setText(contratos.getIdContrato()+" ");
        LocalDate ldI = LocalDate.parse(contratos.getFechaInicio()); // parsea la fecha "2025-08-051"
        String fechaInicioFormateada = ldI.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        binding.tvfechaInicio.setText(fechaInicioFormateada);
        LocalDate ldF = LocalDate.parse(contratos.getFechaFinalizacion());
        String fechaFinFormateada = ldF.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        binding.tvfechaFin.setText(fechaFinFormateada);
        binding.tvEstado.setText(contratos.isEstado()?"Activo": "Inactivo");
        binding.tvInquilinoNombre.setText(contratos.getInquilino().getNombre() +" " + contratos.getInquilino().getApellido()+"");
        binding.tvMonto.setText(contratos.getMontoAlquiler()+" ");
    }
});

       return binding.getRoot();
    }


}