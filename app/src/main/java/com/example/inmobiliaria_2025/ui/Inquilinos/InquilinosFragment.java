package com.example.inmobiliaria_2025.ui.Inquilinos;

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
import com.example.inmobiliaria_2025.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_2025.databinding.FragmentInquilinosBinding;
import com.example.inmobiliaria_2025.modelos.Inquilinos;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel vm;
    private FragmentInquilinosBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm=new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = FragmentInquilinosBinding.inflate(inflater,container,false);

        vm.ObtenerInquilino(getArguments());

        vm.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilinos>() {
            @Override
            public void onChanged(Inquilinos inquilinos) {
                binding.tvApellidoInq.setText(inquilinos.getApellido());
                binding.tvNombreInq.setText(inquilinos.getNombre());
                binding.tvDniInq.setText(inquilinos.getDni());
                binding.tvTelInq.setText(inquilinos.getTelefono());
                binding.tvEmailInq.setText(inquilinos.getEmail());

            }
        });

        return   binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}