package com.example.inmobiliaria_2025.ui.Contratos;



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

import com.bumptech.glide.Glide;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.modelos.Inmuebles;
import com.example.inmobiliaria_2025.request.ApiClient;

import java.util.List;


public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ContratoViewHolder> {
    private Context context;
    private List<Inmuebles> lista;
    private LayoutInflater layoutInflater;
    public ContratosAdapter(List<Inmuebles> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater= layoutInflater;
    }

    @NonNull
    @Override
    public ContratosAdapter.ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.contrato_card, parent, false);
        return new ContratoViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull ContratosAdapter.ContratoViewHolder holder, int position) {
        Inmuebles i = lista.get(position);
        holder.tvDireccionInmCon.setText(i.getDireccion());
        holder.tvTipoInmCon.setText(i.getTipo());
        holder.tvPrecioInmCon.setText(String.valueOf(i.getValor()));
        Glide.with(context)
                .load(ApiClient.URLBASE + i.getImagen())
                .placeholder(R.drawable.contratos)
                .error("null")
                .into(holder.imgInmuebleContrato);

        holder.cardViewInmCon.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putInt("contrato", i.getIdInmueble());
            Navigation.findNavController(v).navigate(R.id.nav_detalleContratosFragment, bundle);
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ContratoViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionInmCon, tvTipoInmCon, tvPrecioInmCon;
        private ImageView imgInmuebleContrato;
        private CardView cardViewInmCon;

        public ContratoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionInmCon = itemView.findViewById(R.id.tvDireccionInmCon);
            tvTipoInmCon = itemView.findViewById(R.id.tvTipoInmCon);
            tvPrecioInmCon = itemView.findViewById(R.id.tvPrecioInmCon);
            imgInmuebleContrato = itemView.findViewById(R.id.imgContratoVigente);
            cardViewInmCon = itemView.findViewById(R.id.cardContrato);
        }
    }

}



