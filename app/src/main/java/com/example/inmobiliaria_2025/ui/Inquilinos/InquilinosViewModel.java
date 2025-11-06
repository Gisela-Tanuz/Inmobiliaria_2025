package com.example.inmobiliaria_2025.ui.Inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmobiliaria_2025.modelos.Inquilinos;

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Inquilinos> MInquilino = new MutableLiveData<>();
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
    }
    public LiveData<Inquilinos> getInquilino()
    {
        return MInquilino;
    }

    public void ObtenerInquilino(Bundle bundle)
    {
        Inquilinos i = (Inquilinos) bundle.getSerializable("inquilino");
        MInquilino.postValue(i);

    }

}