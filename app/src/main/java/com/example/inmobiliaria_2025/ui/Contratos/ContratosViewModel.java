package com.example.inmobiliaria_2025.ui.Contratos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.modelos.Contratos;
import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private  MutableLiveData<List<Inmuebles>> mlistaContratosVig = new MutableLiveData<>();
    private MutableLiveData<String> mMensaje = new MutableLiveData<>();
    private Context context;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<List<Inmuebles>> getListaContratosVigentes() {
        return mlistaContratosVig;
    }
    public LiveData<String> getMensaje() { return mMensaje; }

   public  void LeerInmueblesPorContratos()
   {
     String token = ApiClient.leerToken(context);
     ApiClient.InmoServicios api = ApiClient.getApi();

       Call<List<Inmuebles>> llamada = api.obtenerContratosVigentes("Bearer " + token  );
       llamada.enqueue(new Callback<List<Inmuebles>>() {
           @Override
           public void onResponse(Call<List<Inmuebles>> call, Response<List<Inmuebles>> response) {
               if(response.isSuccessful())
               {
                   if(response.body().size() > 0 ) {
                       mlistaContratosVig.postValue(response.body());
                       mMensaje.postValue("");
                   }else{
                       mMensaje.postValue("No hay Contratos vigentes disponibles");

                   }
               }else
               {
                   mMensaje.postValue("No  se enconto un contrato");
                   Log.d("Contrato", "Error: " + response.message());

               }
           }

           @Override
           public void onFailure(Call<List<Inmuebles>> call, Throwable t) {
               Toast.makeText(context, "Error en el servicio: "+ t.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });








   }


}