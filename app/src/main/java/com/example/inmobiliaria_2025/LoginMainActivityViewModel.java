package com.example.inmobiliaria_2025;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_2025.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginMainActivityViewModel extends AndroidViewModel {

    private Context context;
    public MutableLiveData<Boolean> mLogin = new MutableLiveData<>();
    public  MutableLiveData<Integer>mMensaje = new MutableLiveData<>();
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

    public void Ingresar(String usuario, String pass)
    {
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

                    }else
                    {
                        mMensaje.postValue(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getApplication(),"Error en el servicio",Toast.LENGTH_LONG).show();
                    Log.d("Login", "Error de red" + t.getMessage());
                }
            });

      }catch (NumberFormatException ex)
       {
        Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();        }


    }
}
