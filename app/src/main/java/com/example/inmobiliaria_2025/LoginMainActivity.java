package com.example.inmobiliaria_2025;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_2025.databinding.ActivityLoginMainBinding;

public class LoginMainActivity extends AppCompatActivity {
private LoginMainActivityViewModel vm;
private ActivityLoginMainBinding binding;
public MainActivitySensor ms;
private SensorManager sensorManager;
private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginMainActivityViewModel.class);
        binding = ActivityLoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //permisos para el sensor
        ms = new MainActivitySensor(getApplicationContext());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) ;

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1004);
        vm.getMensajeValidacion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensaje.setText(s);

            }
        });

        vm.getMensaje().observe(this, new Observer<Integer>() {
         @Override
         public void onChanged(Integer integer) {
             binding.tvMensaje.setVisibility(integer);
         }
     });

     vm.getLogin().observe(this, new Observer<Boolean>() {
         @Override
         public void onChanged(Boolean aBoolean) {
             Intent i = new Intent(getApplicationContext(),MenuNavegable.class);
             startActivity(i);
         }
     });
     binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             vm.Ingresar(binding.etUsuario.getText().toString(), binding.etPass.getText().toString());
         }
     });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null) {
            sensorManager.registerListener(ms, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(ms);
    }

}