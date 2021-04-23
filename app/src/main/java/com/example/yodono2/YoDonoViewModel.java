package com.example.yodono2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yodono2.BD.YoDonoRepositorio;
import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Notificaciones;
import com.example.yodono2.Entidades.Relaciones.SolicitudConDonantes;
import com.example.yodono2.Entidades.Solicitudes;

import java.util.List;

public class YoDonoViewModel extends AndroidViewModel {

    private YoDonoRepositorio repositorio;
    private LiveData<List<Donantes>> lista_donantes;
    private LiveData<List<Solicitudes>> lista_solicitudes;
    private LiveData<List<Notificaciones>> lista_Notificaciones;

    public YoDonoViewModel(@NonNull Application application) {
        super(application);
        repositorio = new YoDonoRepositorio(application);
        lista_donantes = repositorio.getAllDonantes();
    }

    //-------------------DONANTES---------------------------------//
    public void insert( Donantes donante ) {
        repositorio.insert(donante);
    }

    public void update( Donantes donante ) {
        repositorio.insert(donante);
    }

    public Donantes buscarDonante( String cedula ) {
        return repositorio.buscarDonante( cedula );
    }

    public Donantes buscarDonante( String cedula, String contrasena ) {
        return repositorio.buscarDonante( cedula );
    }

    public LiveData<List<Donantes>> getListaOtrosDonantes( String cedula ) {
        return repositorio.getListaOtrosDonantes( cedula );
    }

    public LiveData<List<Donantes>> getDonantesPorFiltros( String departamento, String grupo_sanguineo, String cedula ) {
        return repositorio.getDonantesPorFiltro( departamento, grupo_sanguineo, cedula );
    }

    public LiveData<List<Solicitudes>> getSolicitudesDonante(String cedula ) {
        return repositorio.getSolicitudesDonante( cedula );
    }

    public LiveData<List<Donantes>> getLista_donantes() {
        return lista_donantes;
    }

    public LiveData<List<Donantes>> getDonantesCompatibles( String cedula, List<String> grupos) {
        return repositorio.getDonantesCompatibles( cedula, grupos );
    }

    //-------------------SOLICITUDES---------------------------------//
    public void insert( Solicitudes solicitud ) { repositorio.insert(solicitud);}

    public LiveData<List<Solicitudes>> getSolicitudes() {
        return repositorio.getSolicitudes();
    }

    public LiveData<List<Solicitudes>> getSolicitudesNotLogueado( String cedula ) {
        return repositorio.getSolicitudesNotLogueado( cedula );
    }

    public List<SolicitudConDonantes> getDonaciones() {
        return repositorio.getDonaciones();
    }

    public List<SolicitudConDonantes> getDonaciones( Integer id ) {
        return repositorio.getDonaciones( id );
    }

    public void agregarDonacion( SolicitudConDonantes solicitudConDonantes ) {
        repositorio.agregarDonacion( solicitudConDonantes );
    }

    public Solicitudes getSolicitud(int id){
        return repositorio.getSolicitud(id);
    }

    //-------------------Notificaciones-----------------------------//
    public void insert(Notificaciones notificacion){
        repositorio.insert(notificacion);
    }

    public void update(Notificaciones notificacion){
        repositorio.update(notificacion);
    }

    public List<Notificaciones> getLista_Notificaciones(String cedula){
        return repositorio.getListaNotificaciones(cedula);
    }


}
