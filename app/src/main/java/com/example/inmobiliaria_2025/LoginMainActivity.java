package com.example.inmobiliaria_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_2025.databinding.ActivityLoginMainBinding;

public class LoginMainActivity extends AppCompatActivity {
private LoginMainActivityViewModel vm;
private ActivityLoginMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginMainActivityViewModel.class);
        binding = ActivityLoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
}