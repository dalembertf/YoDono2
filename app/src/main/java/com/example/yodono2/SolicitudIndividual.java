package com.example.yodono2;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Notificaciones;
import com.example.yodono2.Entidades.Relaciones.SolicitudConDonantes;
import com.example.yodono2.Entidades.Solicitudes;

import java.util.List;

public class SolicitudIndividual extends AppCompatActivity {

    private TextView text_id;
    private TextView text_fecha;
    private TextView text_departamento;
    private TextView text_grupo;
    private TextView text_donantes_requeridos;
    private TextView text_cantidad_donaciones;
    private TextView text_hospital;
    private TextView text_motivo;
    private Button boton_participar;
    private Solicitudes solicitud;
    private Donantes donante_logueado;
    private Notificaciones notificacion;
    private CompatibilidadSanguinea compatibilidadSanguinea;
    private YoDonoViewModel yoDonoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_individual);

        yoDonoViewModel = new ViewModelProvider( this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getApplication()))
                .get(YoDonoViewModel.class);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        solicitud =  (Solicitudes) bundle.get("Solicitud");
        donante_logueado = (Donantes) bundle.get("Donante");
        compatibilidadSanguinea = new CompatibilidadSanguinea();

        notificacion = new Notificaciones(solicitud.getId(),donante_logueado.getCedula());

        text_id = (TextView)findViewById(R.id.SolicitudIndividualID);
        text_id.setText( "# " + solicitud.getId() );

        text_fecha = (TextView)findViewById(R.id.SolicitudIndividualFechaLim);
        String fecha = solicitud.getFecha_limite();
        String anio = fecha.substring(0,4);
        String mes = fecha.substring(4,6);
        String dia = fecha.substring(6, 8);
        text_fecha.setText( dia + "/" + mes + "/" + anio );

        text_departamento = (TextView)findViewById(R.id.SolicitudIndividualDepartamento);
        text_departamento.setText( solicitud.getDepartamento());

        text_grupo = (TextView)findViewById(R.id.SolicitudIndividualGrupo);
        text_grupo.setText( solicitud.getGrupo_sanguineo());

        text_cantidad_donaciones = (TextView)findViewById(R.id.SolicitudIndividualDonaciones);
        text_cantidad_donaciones.setText( cantidadDonaciones( solicitud.getId() ).toString() );

        text_donantes_requeridos = (TextView)findViewById(R.id.SolicitudIndividualDonantesReq);
        text_donantes_requeridos.setText( "de " + solicitud.getCantidad_donantes());


        text_hospital = (TextView)findViewById(R.id.SolicitudIndividualHospital);
        text_hospital.setText( solicitud.getHospital());

        boton_participar = (Button)findViewById(R.id.BotonParticiparSolicitud);
        if ( donante_logueado.getCedula().compareTo( solicitud.getCedula()) == 0 ) {
            boton_participar.setVisibility(View.GONE);
        }
        else if ( ! solicitudAbierta( solicitud.getId() ))
        {
            deshabilitarBotonParticipar( "Solicitud completa");
        }
        else if ( yaParticipa( solicitud.getId())) {
            deshabilitarBotonParticipar( "Ya participa");
        }
        else if ( ! esCompatible( solicitud.getGrupo_sanguineo(), donante_logueado.getGrupo_Sanguineo() )) {
            deshabilitarBotonParticipar( "No es compatible con " + solicitud.getGrupo_sanguineo() );
        }

        boton_participar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yoDonoViewModel.agregarDonacion(
                        new SolicitudConDonantes(
                                solicitud.getId(),
                                donante_logueado.getCedula()));

                deshabilitarBotonParticipar( "Ya participa" );
                String donaciones_previas = text_cantidad_donaciones.getText().toString();
                Integer donaciones_nuevas = Integer.parseInt( donaciones_previas ) + 1 ;
                text_cantidad_donaciones.setText( donaciones_nuevas.toString() );
                if ( ! solicitudAbierta( solicitud.getId() )) {
                    //crea notificacion pendiente en tabla
                    yoDonoViewModel.insert(notificacion);
                }
            }});
    }

    private void deshabilitarBotonParticipar( String texto ) {
        boton_participar.setEnabled( false );
        boton_participar.setText( texto );
        boton_participar.setBackgroundColor(Color.LTGRAY );
    }

    private Integer cantidadDonaciones( Integer id ) {
        return yoDonoViewModel.getDonaciones( id ).size();
    }

    private Boolean esCompatible( String grupo_receptor, String grupo_donantes ) {
        if ( ! compatibilidadSanguinea.esCompatible(grupo_receptor, grupo_donantes )) {
            return false;
        }
        return true;
    }

    private Boolean yaParticipa( Integer id ) {
       List<SolicitudConDonantes> solicitudConDonantes = yoDonoViewModel.getDonaciones( id );
        Boolean ya_participa = false;

       for ( SolicitudConDonantes donacion : solicitudConDonantes )
       {
           if ( donacion.cedula.compareTo( donante_logueado.getCedula() ) == 0 )
           {
               ya_participa = true;
           }
       }
       return ya_participa;
    }

    private Boolean solicitudAbierta( Integer id ) {
        List<SolicitudConDonantes> solicitudConDonantes = yoDonoViewModel.getDonaciones( id );
        Integer cantidad_donantes_necesarios = Integer.parseInt( solicitud.getCantidad_donantes() );

        if ( solicitudConDonantes.size() < cantidad_donantes_necesarios )
        {
            return true;
        }
        return false;
    }
}
