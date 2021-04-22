package com.example.yodono2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Solicitudes;

import java.util.List;


public class MisSolicitudes extends Fragment implements SolicitudesAdapter.OnSolicitudListener {

    Intent intent;
    SolicitudesAdapter adapter;
    private YoDonoViewModel yoDonoViewModel;

    private RecyclerView recyclerView;
    private List<Solicitudes> lista_solicitudes;
    Donantes donante_logueado;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.ListRecyclerViewSolicitudes);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SolicitudesAdapter( this );
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        intent = getActivity().getIntent();
        Bundle bd = intent.getExtras();

        donante_logueado = (Donantes)bd.get("Donante");

        yoDonoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getActivity().getApplication()))
                .get(YoDonoViewModel.class);

        actualizar_lista_solicitudes();
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void actualizar_lista_solicitudes() {
        yoDonoViewModel.getSolicitudesDonante( donante_logueado.getCedula() ).observe(getViewLifecycleOwner(), new Observer<List<Solicitudes>>() {
            @Override
            public void onChanged(List<Solicitudes> solicitudes) {
                lista_solicitudes = solicitudes;
                adapter.setSolicitudes( solicitudes );
            }
        });
    }

    @Override
    public void onSolicitudClick(int posicion) {
        Solicitudes solicitud_clickeada = lista_solicitudes.get( posicion );
        Intent intent = new Intent( this.getContext(), SolicitudIndividual.class);
        intent.putExtra( "Solicitud", solicitud_clickeada );
        intent.putExtra( "Donante", donante_logueado );
        startActivity( intent );
    }
}