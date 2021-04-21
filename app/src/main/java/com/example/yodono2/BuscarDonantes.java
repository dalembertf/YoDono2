package com.example.yodono2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yodono2.Entidades.Donantes;

import java.util.List;

public class BuscarDonantes extends Fragment {


    private RecyclerView recyclerView;
    private Spinner spinner_departamentos, spinner_grupos_sanguineos;
    private Button btn_filtros, btn_compatibles;
    private TextView lista_grupos_compatibles;
    private DonantesAdapter adapter;
    private LiveData<List<Donantes>> lista_donantes;
    private Donantes donante_logueado;
    private Intent intent;
    private YoDonoViewModel yoDonoViewModel;

    public BuscarDonantes() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar_donantes, container, false);


        spinner_departamentos = (Spinner) view.findViewById(R.id.spinner_departamentos);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_departamentos = ArrayAdapter.createFromResource(getContext(),
                R.array.array_departamentos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_departamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_departamentos.setAdapter(adapter_departamentos);
        spinner_departamentos.setEnabled(false);

        spinner_grupos_sanguineos = (Spinner) view.findViewById(R.id.spinner_grupo_sanguineos);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_grupos_sanguineos = ArrayAdapter.createFromResource(getContext(),
                R.array.array_grupos_sanguineos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_grupos_sanguineos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_grupos_sanguineos.setAdapter(adapter_grupos_sanguineos);

        spinner_departamentos.setOnItemSelectedListener(new MyOnItemSelectedListener());
        spinner_grupos_sanguineos.setOnItemSelectedListener(new MyOnItemSelectedListener());
        spinner_grupos_sanguineos.setEnabled(false);

        btn_filtros = (Button) view.findViewById(R.id.btn_aplicar_filtros);
        btn_compatibles = (Button) view.findViewById(R.id.btn_compatibles);

        btn_filtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_departamentos.setEnabled(true);
                spinner_grupos_sanguineos.setEnabled(true);
                lista_donantes_filtros();
            }
        });

        btn_compatibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_departamentos.setEnabled(false);
                spinner_grupos_sanguineos.setEnabled(false);
                lista_grupos_compatibles = (TextView)view.findViewById(R.id.lista_compatibles);

                CompatibilidadSanguinea compatibilidad = new CompatibilidadSanguinea();
                List<String> grupos_compatibles = compatibilidad.getGruposCompatibles( donante_logueado.getGrupo_Sanguineo() );

                String grupos_texto = "Grupos compatibles:\n";
                for( String grupo: grupos_compatibles ) {
                    grupos_texto = grupos_texto + grupo + " ";
                }
                lista_grupos_compatibles.setText(grupos_texto);

                lista_donantes_compatibles( grupos_compatibles );
            }
        });

        recyclerView = view.findViewById(R.id.ListRecyclerViewBuscarDonantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new DonantesAdapter();
        recyclerView.setAdapter(adapter);

        intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        donante_logueado = (Donantes)bundle.get("Donante");

        yoDonoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getActivity().getApplication()))
                .get(YoDonoViewModel.class);

        iniciar_lista_donantes();
        return view;
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if ( spinner_departamentos.isEnabled() ) {
                lista_donantes_filtros();
            }
        }
    }

    public void lista_donantes_compatibles( List<String> grupos ) {

        yoDonoViewModel.getDonantesCompatibles(
                donante_logueado.getCedula(),
                grupos).observe( getViewLifecycleOwner(),
                new Observer<List<Donantes>>() {

                    @Override
                    public void onChanged(List<Donantes> donantes) {
                        adapter.setLista_donantes(donantes);
                    }
                });
    }
    public void lista_donantes_filtros() {

        yoDonoViewModel.getDonantesPorFiltros(
                spinner_departamentos.getSelectedItem().toString(),
                spinner_grupos_sanguineos.getSelectedItem().toString(),
                donante_logueado.getCedula() ).observe( getViewLifecycleOwner(),
                new Observer<List<Donantes>>() {

            @Override
            public void onChanged(List<Donantes> donantes) {
                adapter.setLista_donantes(donantes);

            }
        });
    }

    public void iniciar_lista_donantes() {

        yoDonoViewModel.getListaOtrosDonantes( donante_logueado.getCedula() ).observe( getViewLifecycleOwner(), new Observer<List<Donantes>>() {
            @Override
            public void onChanged(List<Donantes> donantes) {
                    adapter.setLista_donantes(donantes);

            }
        });
    }
}