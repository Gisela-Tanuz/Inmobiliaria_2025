package com.example.inmobiliaria_2025.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {


    private final MutableLiveData<List<Inmuebles>> mlistaInmuebles = new MutableLiveData<>();
   private Context context;
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<List<Inmuebles>> getListaInmuebles() {
        return mlistaInmuebles;
    }



    public  void leerInmuebles()
    {
      String token = ApiClient.leerToken(context);    ApiClient.InmoServicios api = ApiClient.getApi();
       Call<List<Inmuebles>> llamada = api.obtenerInmuebles("Bearer" + token );
llamada.enqueue(new Callback<List<Inmuebles>>() {
    @Override
    public void onResponse(Call<List<Inmuebles>> call, Response<List<Inmuebles>> response) {
        if (response.isSuccessful())
        {
            mlistaInmuebles.postValue(response.body());
        }else
        {

            Toast.makeText(getApplication(), "No hay inmuebles disponibles", Toast.LENGTH_SHORT).show();
            Log.d("Inmueble", "Error " + response.message());
        }
    }

    @Override
    public void onFailure(Call<List<Inmuebles>> call, Throwable t) {
        Toast.makeText(getApplication(), "Error en el servicio", Toast.LENGTH_SHORT).show();
    }
});



    }
}