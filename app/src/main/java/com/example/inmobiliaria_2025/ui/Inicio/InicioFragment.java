package com.example.inmobiliaria_2025.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_2025.LoginMainActivityViewModel;
import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel vm;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vm=  new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);

        vm.getMapaInmobiliaria().observe(getViewLifecycleOwner(), new Observer<InicioViewModel.Mapa>() {
            @Override
            public void onChanged(InicioViewModel.Mapa mapa) {
                SupportMapFragment supportMapFragment =
                        (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
                supportMapFragment.getMapAsync(mapa);

            }
        });

        vm.cargarMapa();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}