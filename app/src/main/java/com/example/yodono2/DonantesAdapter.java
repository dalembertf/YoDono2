package com.example.yodono2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yodono2.Entidades.Donantes;

import java.util.ArrayList;
import java.util.List;

public class DonantesAdapter extends RecyclerView.Adapter<DonantesAdapter.DonanteHolder> {
    private List<Donantes> lista_donantes = new ArrayList<>();

    @NonNull
    @Override
    public DonanteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iteamView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element_listadonantes, parent, false);
        return new DonanteHolder(iteamView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonanteHolder holder, int position) {
        Donantes donante_actual = lista_donantes.get(position);
        holder.textViewNombre.setText(donante_actual.getNombre() + " " + donante_actual.getApellido());
        holder.textViewGrupo.setText(donante_actual.getGrupo_Sanguineo());
        holder.textViewDepartamento.setText(donante_actual.getDepartamento());

    }

    @Override
    public int getItemCount() {
        return lista_donantes.size();
    }

    public void setLista_donantes(List<Donantes> lista_donantes) {
        this.lista_donantes = lista_donantes;
        notifyDataSetChanged();
    }

    class DonanteHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private TextView textViewGrupo;
        private TextView textViewDepartamento;

        public DonanteHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.BDnombreTV);
            textViewGrupo = itemView.findViewById(R.id.BDgrupoTV);
            textViewDepartamento = itemView.findViewById(R.id.BDdepartamentoTV);
        }
    }
}
