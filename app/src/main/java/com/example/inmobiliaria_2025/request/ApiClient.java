package com.example.inmobiliaria_2025.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.modelos.Propietarios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public class ApiClient {

public static  final String URLBASE= "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";
//public static  final String URLBASE= "http://192.168.100.9:5000/";


    public static InmoServicios getApi()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                // .addConverterFactory(ScalarsConverterFactory.create()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(InmoServicios.class);
    }
    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        return sp.getString("token", null);
    }
    public  interface InmoServicios
    {

        @FormUrlEncoded
        @POST("api/Propietarios/login")
        //@POST("api/login")
       Call<String> login(@Field("Usuario") String u, @Field("Clave") String c);
      //  Call<String> login(@Field("Email") String u, @Field("Clave") String c);


        @GET("api/Propietarios")
        Call<Propietarios> getPropietario(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar")
        Call<Propietarios>actualizarPropietarios(@Header("Authorization") String token,@Body Propietarios p);

        @GET("api/Inmuebles")
        Call<List<Inmuebles>> obtenerInmuebles(@Header("Authorization") String token);

        @PUT("api/Inmuebles/actualizar")
        Call<Inmuebles> actualizarInmueble(@Header ("Authorization") String token , @Body Inmuebles inmueble);

        @Multipart
        @POST("api/Inmuebles/cargar")
        Call<Inmuebles> cargarInmuebles(@Header("Authorization") String token, @Part MultipartBody.Part imagen, @Part("inmueble")RequestBody inmueble);
    }
}
