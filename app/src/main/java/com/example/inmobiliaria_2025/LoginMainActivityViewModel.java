package com.example.inmobiliaria_2025;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.modelos.Propietarios;
import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMainActivityViewModel extends AndroidViewModel {

    private Context context;
    public MutableLiveData<Boolean> mLogin = new MutableLiveData<>();
    public  MutableLiveData<Integer>mMensaje = new MutableLiveData<>();
    private MutableLiveData<String> mMensajeValidacion = new MutableLiveData<>();
    private  MutableLiveData<Propietarios> mPropietario = new MutableLiveData<>();
    public LoginMainActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }

    public LiveData<Boolean> getLogin()
    {
        return  mLogin;
    }
    public LiveData<Integer> getMensaje()
    {
        return  mMensaje;
    }
    public LiveData<String> getMensajeValidacion()
    {
        return  mMensajeValidacion;
    }
    public LiveData<Propietarios> getMPropietario()
    {
        return  mPropietario;
    }

    public void Ingresar(String usuario, String pass)
    {
        //Validaciones
        if (usuario == null || usuario.trim().isEmpty()) {
            mMensajeValidacion.postValue("Debe ingresar un usuario");

            mMensaje.postValue(View.VISIBLE);
            return;
        }

        if (pass == null || pass.trim().isEmpty()) {
            mMensajeValidacion.postValue("Debe ingresar una contraseña");

            mMensaje.postValue(View.VISIBLE);
            return;
        }

       try{
            ApiClient.InmoServicios api = ApiClient.getApi();
            Call<String> llamada = api.login(usuario,pass);
            llamada.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful())
                    {
                        String token = response.body();

                        ApiClient.guardarToken(context, token);
                        mLogin.postValue(true);
                        mMensaje.postValue(View.INVISIBLE);
                        mMensajeValidacion.postValue("");

                    }else
                    {
                        mMensaje.postValue(View.VISIBLE);
                        mMensajeValidacion.postValue("Usuario o clave incorrecta");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    mMensaje.postValue(View.VISIBLE);
                    mMensajeValidacion.postValue("Error en el servicio");

                    Log.d("Login", "Error de red" + t.getMessage());
                }
            });

      }catch (NumberFormatException ex)
       {
        Toast.makeText(getApplication(),"Error en el servicio",Toast.LENGTH_LONG).show();
       }


    }

}
