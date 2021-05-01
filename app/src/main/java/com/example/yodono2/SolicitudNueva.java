package com.example.yodono2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Solicitudes;


public class SolicitudNueva extends Fragment {

    Button boton_solicitar_aceptar;

    private YoDonoViewModel yoDonoViewModel;

    DatePicker picker_fecha_limite;
    EditText text_hospital;
    EditText text_cantidad_donantes;
    EditText text_motivo;
    HorizontalNumberPicker number_cantidad_donantes;

    Donantes donante_logueado;

    public SolicitudNueva() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bd = getActivity().getIntent().getExtras();
        donante_logueado = (Donantes) bd.get("Donante");

        yoDonoViewModel = new ViewModelProvider( this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getActivity().getApplication()))
                .get(YoDonoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solicitud_nueva, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        TextView miGrupoSanguineo = (TextView)view.findViewById(R.id.text_miGrupoSanguineo);
        miGrupoSanguineo.setText(donante_logueado.getGrupo_Sanguineo());

        number_cantidad_donantes = (HorizontalNumberPicker) view.findViewById(R.id.number_donantes);

        TextView miDepartamento = (TextView)view.findViewById(R.id.text_miDepartamento);
        miDepartamento.setText(donante_logueado.getDepartamento());

        Button boton_solicitar_aceptar = (Button)view.findViewById(R.id.boton_solicitar_aceptar);

        boton_solicitar_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_hospital = (EditText)view.findViewById(R.id.text_hospital);
                text_motivo = (EditText)view.findViewById(R.id.text_motivo);
                String hospital = text_hospital.getText().toString();
                Integer cantidad_int = number_cantidad_donantes.getValue();
                String cantidad = cantidad_int.toString();
                String motivo = text_motivo.getText().toString();

                String cedula = donante_logueado.getCedula();
                String nombre = donante_logueado.getNombre();
                String apellido = donante_logueado.getApellido();
                String departamento = donante_logueado.getDepartamento();
                String grupo_sanguineo = donante_logueado.getGrupo_Sanguineo();

                picker_fecha_limite = (DatePicker)view.findViewById(R.id.fecha_limite);
                Integer mes_i, dia_i, anio_i;
                String mes, dia, anio, fecha;
                mes_i = picker_fecha_limite.getMonth();
                mes = mes_i.toString();
                if ( mes_i < 10 ) { mes = '0' + mes; }
                dia_i = picker_fecha_limite.getDayOfMonth();
                dia = dia_i.toString();
                if ( dia_i < 10 ) { dia = '0' + dia; }
                anio_i = picker_fecha_limite.getYear();
                anio = anio_i.toString();

                fecha = anio + mes + dia;

                if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || fecha.isEmpty() || hospital.isEmpty() || cantidad.isEmpty() || motivo.isEmpty() )
                {
                    Toast.makeText(getActivity(), "Debe completar todos los datos", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Solicitudes nueva_solicitud = new Solicitudes( cedula, nombre, apellido, grupo_sanguineo, hospital, fecha, motivo, cantidad, departamento );

                    yoDonoViewModel.insert( nueva_solicitud );

                    Toast.makeText(getActivity(), "Solicitud Agregada", Toast.LENGTH_SHORT).show();

                    navController.navigate(R.id.misSolicitudes);

                }

            }
        });
    }
}