package com.example.inmobiliaria_2025.ui.Contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.modelos.Contratos;
import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Contratos> MContrato = new MutableLiveData<>();
    public DetalleContratosViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<Contratos> getContratos()
    {
        return  MContrato;
    }
    public void ObtenerContrato(Bundle bundle) {

        int idInmueble =  bundle.getInt("contrato");
        ObtenerDetalleContrato(idInmueble);
    }
    private void ObtenerDetalleContrato(int idInmueble)
    {

        String token = ApiClient.leerToken(context);

        Call<Contratos> llamada = ApiClient.getApi().obtenerContratosPorInmuebles("Bearer " + token, idInmueble);
        llamada.enqueue(new Callback<Contratos>() {
            @Override
            public void onResponse(Call<Contratos> call, Response<Contratos> response) {
                if(response.isSuccessful())
                {
                    MContrato.postValue( response.body());
                }
            }

            @Override
            public void onFailure(Call<Contratos> call, Throwable t) {

            }
        });


    }
}