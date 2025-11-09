package com.example.inmobiliaria_2025.ui.Pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.modelos.Pagos;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pagos>> MPagos= new MutableLiveData<>();
    private MutableLiveData<String> MmesajePagos = new MutableLiveData<>();
    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }
    public LiveData<List<Pagos>> getListaPagos()
    {
        return MPagos;
    }
    public LiveData<String> getMensajePagos()
    {
        return MmesajePagos;
    }

    public void ObtenerPagos(Bundle bundle) {

            int idContrato = bundle.getInt("pagos");
            if(idContrato != -1) {
                ObtenerPagosPorContratos(idContrato);
            }else{
                MmesajePagos.postValue("No hay pagos para mostrar");
            }
    }
    private void ObtenerPagosPorContratos(int idContrato)
    {
        String token = ApiClient.leerToken(context);
        Call<List<Pagos>> llamada =  ApiClient.getApi().ObtenerPagosPorContratos("Bearer " + token, idContrato);
        llamada.enqueue(new Callback<List<Pagos>>() {
            @Override
            public void onResponse(Call<List<Pagos>> call, @NonNull Response<List<Pagos>> response) {
                if(response.isSuccessful()) {
                    if(response.body().size() > 0 ) {

                        MPagos.postValue(response.body());
                        MmesajePagos.postValue("");
                    } else{

                        MmesajePagos.postValue("No hay pagos para mostrar");
                    }
                }else
                {
                    Log.d("Pagos", "Error HTTP: " + response.code() + " - " + response.message());
                    MmesajePagos.postValue("Error al obtener el pago");

                }
            }

            @Override
            public void onFailure(Call<List<Pagos>> call, Throwable t) {
                Toast.makeText(context, "Error de contexto: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



}