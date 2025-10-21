package com.example.inmobiliaria_2025.ui.Inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;
import com.bumptech.glide.Glide;
import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.InmueblesViewHolder> {
    private Context context;
    private List<Inmuebles> lista;
    public InmueblesAdapter(List<Inmuebles> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public InmueblesAdapter.InmueblesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble_card, parent, false);
        return new InmueblesViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull InmueblesAdapter.InmueblesViewHolder holder, int position) {
        Inmuebles inmuebles = lista.get(position);
        holder.tvDireccion.setText(inmuebles.getDireccion());
        holder.tvTipo.setText(inmuebles.getTipo());
        holder.tvPrecio.setText(String.valueOf(inmuebles.getValor()));
        Glide.with(context)
                .load(ApiClient.URLBASE + inmuebles.getImagen())
                .placeholder(R.drawable.inmuebles)
                .error("null")
                .into(holder.imgInmueble);

        holder.cardView.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmuebles);
            Navigation.findNavController((Activity)v.getContext(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_detalleInmueble, bundle);

        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class InmueblesViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion, tvTipo, tvPrecio;
        private ImageView imgInmueble;
        private CardView cardView;

        public InmueblesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            imgInmueble = itemView.findViewById(R.id.imgInm);
            cardView = itemView.findViewById(R.id.cardInmueble);
        }
    }

}



