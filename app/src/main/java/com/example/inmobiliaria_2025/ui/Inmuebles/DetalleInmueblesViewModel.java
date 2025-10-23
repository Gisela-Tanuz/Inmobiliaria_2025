package com.example.inmobiliaria_2025.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmueblesViewModel extends AndroidViewModel {

    private MutableLiveData <Inmuebles> mInmueble = new MutableLiveData<>();
    private Context context;
    public DetalleInmueblesViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();

    }
public LiveData<Inmuebles> getInmuebles()
{
    return mInmueble;
}

public  void ObtenerDetallesInmuebles(Bundle inmueblebundle)
{
    Inmuebles inmuebles = (Inmuebles)inmueblebundle.getSerializable("inmueble");
    Log.d("Detalle Inmueble","Error" + inmuebles);

    if(inmuebles != null)
    {
      this.mInmueble.setValue(inmuebles);
    }


}


public  void actualizarInmueble(Boolean disponible )
{
    Inmuebles inmuebles = new Inmuebles();
    inmuebles.setDisponible(disponible);
    inmuebles.setIdInmueble(this.mInmueble.getValue().getIdInmueble());

     String token = ApiClient.leerToken(context);
     Call<Inmuebles> llamada = ApiClient.getApi().actualizarInmueble("Bearer " + token, inmuebles);
     llamada.enqueue(new Callback<Inmuebles>() {
         @Override
         public void onResponse(Call<Inmuebles> call, Response<Inmuebles> response) {
           if(response.isSuccessful())
           {

               Toast.makeText(context, "Inmueble actualizado correctamente", Toast.LENGTH_LONG).show();
           }   else
           {
               Toast.makeText(context, "Error  al actualizar el inmueble", Toast.LENGTH_LONG).show();
           }
         }

         @Override
         public void onFailure(Call<Inmuebles> call, Throwable t) {
             Toast.makeText(context, "Error  del servicio", Toast.LENGTH_LONG).show();

         }
     });
}
}