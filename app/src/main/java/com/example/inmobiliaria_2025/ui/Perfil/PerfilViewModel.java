package com.example.inmobiliaria_2025.ui.Perfil;

import static java.util.regex.Pattern.matches;

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

public class PerfilViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mTextoBoton = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mEstado = new MutableLiveData<>();
    private MutableLiveData<Propietarios> mPropietario = new MutableLiveData<>();

    private MutableLiveData<String>mMensaje = new MutableLiveData<>();

    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();

    }

    public LiveData<String> getTextBoton() { return mTextoBoton ; }
    public LiveData<Boolean> getEstado(){ return mEstado; }
    public LiveData<Propietarios> getPropietario()
    {
        return  mPropietario;
    }

    public LiveData<String> getMensaje()
    {
        return  mMensaje;
    }

     public void  obtenerPropietario()
     {
      String  token = ApiClient.leerToken(context);
       Call<Propietarios> llamada =ApiClient.getApi().getPropietario("Bearer " + token);
       llamada.enqueue(new Callback<Propietarios>() {
           @Override
           public void onResponse(Call<Propietarios> call, Response<Propietarios> response) {
               if (response.isSuccessful())
               {
                mPropietario.postValue(response.body());

               }else
               {
                   Toast.makeText(context,"No se pudo obtener el usuario",Toast.LENGTH_LONG).show();

               }
           }

           @Override
           public void onFailure(Call<Propietarios> call, Throwable t) {
               Toast.makeText(context,"Error en el servicio",Toast.LENGTH_LONG).show();

           }
       });

     }
    public void  actualizarPerfil(String boton,String nombre, String apellido, String dni, String mail, String telefono)
    {
           if(boton.equalsIgnoreCase("Editar"))
           {
               mEstado.setValue(true);
               mTextoBoton.setValue("Guardar");
               mMensaje.setValue("");
           }else
           {  //Recupera el propietario
               Propietarios p = new Propietarios();
               p.setIdPropietario(mPropietario.getValue().getIdPropietario());
               p.setNombre(nombre);
               p.setApellido(apellido);
               p.setDni(dni);
               p.setEmail(mail);
               p.setTelefono(telefono);
               mEstado.setValue(false);
               mTextoBoton.setValue("Editar");
               mMensaje.setValue("");
               //validaciones
               if (nombre == null || nombre.trim().isEmpty()) {
                   mMensaje.postValue("Debe ingresar un nombre");

                   return;
               }
               if (apellido == null || apellido.trim().isEmpty()) {
                   mMensaje.postValue("Debe ingresar un apelildo");
                   return;
               }
               if (mail == null || mail.trim().isEmpty()) {
                   mMensaje.postValue("Debe ingresar un email");
                   return;
               }
               if (dni == null || dni.trim().isEmpty()) {
                   mMensaje.postValue("Debe ingresar un dni");
                   return;
               }else
               {
                   if(!dni.matches("\\d+"))
                   {
                       mMensaje.postValue("Debe ingresar un valor numerico en el campo dni");
                       return;

                   }
               }
               if (telefono.isBlank() || telefono.trim().isEmpty()) {
                   mMensaje.postValue("Debe ingresar un valor numerico en el campo teléfono");
                   return;
               }else
               {
                   if(!telefono.matches("\\d+"))
                   {
                       mMensaje.postValue("Debe ingresar un valor numerico en el campo teléfono");
                       return;

                   }

               }
                   String token = ApiClient.leerToken(context);
                    Call<Propietarios> propietariosCall = ApiClient.getApi().actualizarPropietarios("Bearer "+ token, p);
                    propietariosCall.enqueue(new Callback<Propietarios>() {
                        @Override
                        public void onResponse(Call<Propietarios> call, Response<Propietarios> response) {
                            if(response.isSuccessful())
                            {
                                mEstado.setValue(true);
                                mTextoBoton.setValue("Editar");
                                mMensaje.postValue("Propietario actualizado");

                            }else
                            {

                                mMensaje.postValue("Error al actualizar el Propietario");
                            }
                        }

                        @Override
                        public void onFailure(Call<Propietarios> call, Throwable t) {
                            Toast.makeText(context,"Error en el servicio",Toast.LENGTH_LONG).show();

                        }
                    });

           }
    }

}