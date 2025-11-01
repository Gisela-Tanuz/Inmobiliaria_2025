package com.example.inmobiliaria_2025.ui.Inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentAgregarInmueblesBinding;
import com.example.inmobiliaria_2025.databinding.FragmentDetalleInmueblesBinding;

import org.jetbrains.annotations.Contract;

public class AgregarInmueblesFragment extends Fragment {

    private AgregarInmueblesViewModel vm;
    private FragmentAgregarInmueblesBinding binding;
    private ActivityResultLauncher<Intent> arl;
    private Intent intent;
    public static AgregarInmueblesFragment newInstance() {
        return new AgregarInmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentAgregarInmueblesBinding.bind(getLayoutInflater().inflate(R.layout.fragment_agregar_inmuebles, container,false));
        vm = new ViewModelProvider(this).get(AgregarInmueblesViewModel.class);
        vm.getMensajeInmueble().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeInmueble.setText(s);
            }
        });
        abrirGaleria();

        binding.btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            arl.launch(intent);
            }
        });
        vm.getUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.IvFotoAdd.setImageURI(uri);
            }
        });

        binding.btnAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String direccion, String valor, String tipo, String uso, String ambientes, String superficie, boolean disponible

                                     vm.cargarInmueble(binding.etDireccionAdd.getText().toString(),
                                             binding.etPrecioAdd.getText().toString(),
                                             binding.etTipoAdd.getText().toString(),
                                             binding.etUsoAdd.getText().toString(),
                                             binding.etAmbientesAdd.getText().toString(),
                                             binding.etSuperficieAdd.getText().toString(),
                                             binding.CbxDisponibleAdd.isChecked());
            }
        });
        return binding.getRoot();
    }
    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//intent para abrir la galeria
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.recibirFoto(result);
                Log.d("AgregarInmueble", "Result : "+ result);

            }
        });
    }
}