package com.example.inmobiliaria_2025.ui.Inicio;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioViewModel extends AndroidViewModel {

    private final MutableLiveData<Mapa> mMapa = new MutableLiveData<>();

    public InicioViewModel(@NonNull Application application) {
        super(application);

    }
    public LiveData<Mapa> getMapaInmobiliaria() {
        return mMapa;
    }

    public class Mapa implements OnMapReadyCallback {
        LatLng inmob = new LatLng(-33.29690, -66.33100);


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            MarkerOptions marcadorSanLuis = new MarkerOptions();
            marcadorSanLuis.position(inmob);
            marcadorSanLuis.title("Inmobiliaria Tanúz");

            googleMap.addMarker(marcadorSanLuis);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition cameraPosition = new CameraPosition
                    .Builder()
                    .target(inmob)
                    .zoom(20)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);
        }
    }
    public void cargarMapa(){
        Mapa mapaActual = new Mapa();
        mMapa.setValue(mapaActual);
    }

}