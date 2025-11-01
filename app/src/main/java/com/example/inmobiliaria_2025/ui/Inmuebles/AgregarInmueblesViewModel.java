package com.example.inmobiliaria_2025.ui.Inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

 public class AgregarInmueblesViewModel extends AndroidViewModel {
 private MutableLiveData<Uri> Muri = new MutableLiveData<>();
 private MutableLiveData<String>mMensajeInmueble = new MutableLiveData<>();
 private Context context;
    public AgregarInmueblesViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }
 public LiveData<Uri> getUri()
 {
    return Muri;
 }
    public LiveData<String> getMensajeInmueble()
    {
        return  mMensajeInmueble;
    }
    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("foto_url", uri.toString());
            Muri.setValue(uri);
        }
    }

    public void cargarInmueble(String direccion, String valor, String tipo, String uso, String ambientes, String superficie, boolean disponible){

       double precioPars;
       int ambientesPars,superficiePars;

        if(direccion.isEmpty() || tipo.isEmpty() || uso.isEmpty() || ambientes.isEmpty() || superficie.isEmpty() || valor.isEmpty()){
            mMensajeInmueble.postValue("Debe ingresar todos los campos");
            return;
        }else
        {
             mMensajeInmueble.postValue("");
        }

        //Validar formato de numeros
        try{

            precioPars = Double.parseDouble(valor);
            superficiePars = Integer.parseInt(superficie);
            ambientesPars = Integer.parseInt(ambientes);


            if(Muri.getValue() == null) {
                mMensajeInmueble.postValue("Debe seleccionar una foto");

                return;
            }

            Inmuebles inmuebles = new Inmuebles();
            inmuebles.setDireccion(direccion);
            inmuebles.setValor(precioPars);
            inmuebles.setTipo(tipo);
            inmuebles.setUso(uso);
            inmuebles.setAmbientes(ambientesPars);
            inmuebles.setSuperficie(superficiePars);
            inmuebles.setDisponible(disponible);
            //Convertir en base a la uri
            byte[] imagen = transformarImagen();
            String inmueblesJson = new Gson().toJson(inmuebles);
            RequestBody inmueblesBody = RequestBody.create(MediaType.parse(
                    "application/json; charset=utf-8"),inmueblesJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"),
                    imagen);

            //Arma el  multipart

            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg",requestFile);
            ApiClient.InmoServicios api= ApiClient.getApi();
            String token = ApiClient.leerToken(context);

            Call<Inmuebles> llamada = api.cargarInmuebles("Bearer "+ token, imagenPart, inmueblesBody);
            llamada.enqueue(new Callback<Inmuebles>() {
                @Override
                public void onResponse(Call<Inmuebles> call, Response<Inmuebles> response) {
                    if(response.isSuccessful())
                    {
                        mMensajeInmueble.postValue("Inmueble cargado correctamente");
                    }else
                    {
                        mMensajeInmueble.postValue("Error al cargar el Inmueble");
                    }

                }
                @Override
                public void onFailure(Call<Inmuebles> call, Throwable t) {
                    Toast.makeText(context, " Error del servicio ", Toast.LENGTH_LONG).show();
                }
            });


        }catch(NumberFormatException nfe){
            mMensajeInmueble.postValue("Debe ingresar números en los campos de valor, superficie y ambientes");

        }

    }

    private byte[] transformarImagen() {
        try {
            Uri uri = Muri.getValue();  //lo puedo usar porque estoy en viewmodel
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (
                FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }
}