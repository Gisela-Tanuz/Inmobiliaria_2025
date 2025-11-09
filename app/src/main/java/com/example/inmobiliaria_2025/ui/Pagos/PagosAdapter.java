package com.example.inmobiliaria_2025.ui.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_2025.R;
import com.example.inmobiliaria_2025.modelos.Pagos;
import com.example.inmobiliaria_2025.ui.Contratos.ContratosAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.PagosViewHolder> {
    private Context context;
    private List<Pagos> lista;
    private LayoutInflater layoutInflater;

    public PagosAdapter(List<Pagos> lista, Context context, LayoutInflater layoutInflater) {
          this.lista = lista;
          this.context = context;
          this.layoutInflater= layoutInflater;
    }
    @NonNull
    @Override
    public PagosAdapter.PagosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.pagos_card,parent,false);
       return new PagosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.PagosViewHolder holder, int position) {
           Pagos p = lista.get(position);
           holder.tvIdPago.setText(p.getIdPago() +" ");
           LocalDate ldP = LocalDate.parse(p.getFechaPago());
           String fechaPagosFormateada = ldP.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           holder.tvfechaPag.setText(fechaPagosFormateada);
           holder.tvMontoPag.setText(String.valueOf(p.getMonto()));
           holder.tvDetalle.setText(p.getDetalle());
           holder.tvEstadoPag.setText(p.isEstado() ? "Abonado" : "Pendiente");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class PagosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdPago, tvfechaPag, tvMontoPag, tvDetalle ,tvEstadoPag;

        public PagosViewHolder(@NonNull View itemView){
            super(itemView);
            tvIdPago = itemView.findViewById(R.id.tvIdPago);
            tvfechaPag = itemView.findViewById(R.id.tvFechaPago);
            tvMontoPag = itemView.findViewById(R.id.tvMontoPago);
            tvDetalle =  itemView.findViewById(R.id.tvDetallePago);
            tvEstadoPag = itemView.findViewById(R.id.tvEstadoPgo);
        }
    }
}
