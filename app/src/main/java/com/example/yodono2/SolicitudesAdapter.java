package com.example.yodono2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yodono2.Entidades.Solicitudes;

import java.util.ArrayList;
import java.util.List;

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.SolicitudesHolder> {
    private List<Solicitudes> fragment_home = new ArrayList<>();
    private OnSolicitudListener mOnSolicitudListener;

    public SolicitudesAdapter(OnSolicitudListener onSolicitudListener) {
        this.mOnSolicitudListener = onSolicitudListener;
    }

    @NonNull
    @Override
    public SolicitudesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element_solicitudes, parent, false);
        return new SolicitudesHolder(itemView, mOnSolicitudListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudesHolder holder, int position) {
        Solicitudes solicitud_actual = fragment_home.get(position);

        String fecha = solicitud_actual.getFecha_limite();
        String anio = fecha.substring(0,4);
        String mes = fecha.substring(4,6);
        String dia = fecha.substring(6, 8);


        holder.textViewId.setText( "# " + solicitud_actual.getId().toString() );
        holder.textViewFecha.setText( dia + "/" + mes + "/" + anio );
        holder.textViewDepartamento.setText(solicitud_actual.getDepartamento());
        holder.textViewGrupo.setText(solicitud_actual.getGrupo_sanguineo());
        holder.textViewDonantes.setText(solicitud_actual.getCantidad_donantes());

    }

    @Override
    public int getItemCount() {
        return fragment_home.size();
    }

    public void setSolicitudes(List<Solicitudes> fragment_home) {
        this.fragment_home = fragment_home;
        notifyDataSetChanged();
    }

    class SolicitudesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnSolicitudListener onSolicitudListener;

        private TextView textViewId;
        private TextView textViewFecha;
        private TextView textViewGrupo;
        private TextView textViewDepartamento;
        private TextView textViewDonantes;

        public SolicitudesHolder(@NonNull View itemView, OnSolicitudListener onSolicitudListener ) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.solicitudesNumero);
            textViewFecha = itemView.findViewById(R.id.solicitudesFechaLim);
            textViewGrupo = itemView.findViewById(R.id.solicitudesGrupo);
            textViewDepartamento = itemView.findViewById(R.id.solicitudesDepartamento);
            textViewDonantes = itemView.findViewById(R.id.solicitudesDonantesReq);

            this.onSolicitudListener = onSolicitudListener;

            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {
            onSolicitudListener.onSolicitudClick( getAdapterPosition());
        }
    }

    public interface OnSolicitudListener {
        void onSolicitudClick( int posicion );
    }
}
