package com.example.inmobiliaria_2025.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.inmobiliaria_2025.databinding.FragmentPerfilBinding;
import com.example.inmobiliaria_2025.modelos.Propietarios;

public class PerfilFragment extends Fragment {
    private  PerfilViewModel vm;
    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =  new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        vm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietarios>() {
            @Override
            public void onChanged(Propietarios propietarios) {
                binding.etApellido.setText(propietarios.getApellido());
                binding.etNombre.setText(propietarios.getNombre());
                binding.etDni.setText(propietarios.getDni());
                binding.etTelefono.setText(propietarios.getTelefono());
                binding.etEmail.setText(propietarios.getEmail());

            }
        });
        vm.getTextBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnEditar.setText(s);
            }
        });
        vm.getMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajePerfil.setText(s);
            }
        });
        vm.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etApellido.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etDni.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
                binding.etEmail.setEnabled(aBoolean);
            }
        });



        vm.obtenerPropietario();

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            String dni = binding.etDni.getText().toString();
            String mail = binding.etEmail.getText().toString();
            String telefono = binding.etTelefono.getText().toString();

            @Override
            public void onClick(View view) {
                vm.actualizarPerfil(binding.btnEditar.getText().toString(),nombre, apellido,dni,mail, telefono );
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}