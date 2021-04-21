package com.example.yodono2.BD;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.yodono2.Entidades.DonanteConSolicitudes;
import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Relaciones.SolicitudConDonantes;
import com.example.yodono2.Entidades.Solicitudes;
import com.example.yodono2.daos.DonanteDao;
import com.example.yodono2.daos.SolicitudConDonantesDao;
import com.example.yodono2.daos.SolicitudesDao;
public class YoDonoRepositorio {

    private DonanteDao donanteDao;
    private LiveData<List<Donantes>> listaDonantes;

    private SolicitudesDao solicitudesDao;
    private LiveData<List<Solicitudes>> listaSolicitudes;

    private SolicitudConDonantesDao solicitudConDonantesDao;
    private List<SolicitudConDonantes> listaSolicitudConDonantes;

    public YoDonoRepositorio(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        donanteDao = database.getDonanteDao();
        solicitudesDao = database.getSolicitudesDao();
        solicitudConDonantesDao = database.getSolicitudesConDonantesDao();

        listaDonantes = donanteDao.getAllDonanantes();
        listaSolicitudes = solicitudesDao.getAllSolicitudes();

        listaSolicitudConDonantes = solicitudConDonantesDao.getDonaciones();
    }

    public List<SolicitudConDonantes> getDonaciones() {
        return this.listaSolicitudConDonantes;
    }

    public List<SolicitudConDonantes> getDonaciones( Integer id ) {
        return solicitudConDonantesDao.getDonaciones( id );
    }

    public void agregarDonacion( SolicitudConDonantes solicitudConDonantes ) {
        solicitudConDonantesDao.agregar( solicitudConDonantes );
    }

    public void insert(Donantes donante) {
        new InsertDonanteAsyncTask(donanteDao).execute(donante);
    }

    public void update(Donantes donante) {
        new UpdateDonanteAsyncTask(donanteDao).execute(donante);
    }

    public Donantes buscarDonante( String cedula ) {
        return donanteDao.buscarDonante( cedula );
    }

    public Donantes buscarDonante( String cedula, String contrasena ) {
        return donanteDao.buscarDonante( cedula );
    }

    public LiveData<List<Donantes>> getListaOtrosDonantes(String cedula) {
        return donanteDao.buscarDonantesNotLogged( cedula );
    }

    public LiveData<List<Donantes>> getDonantesPorFiltro( String departamento, String grupo_sanguineo, String cedula ) {
        return donanteDao.getDonantesPorFiltros( departamento, grupo_sanguineo, cedula );
    }

    public LiveData<List<Donantes>> getAllDonantes() {
        return listaDonantes;
    }

    public LiveData<List<Donantes>> getDonantesCompatibles( String cedula, List<String> grupos ) {
        return donanteDao.getDonantesCompatibles( cedula, grupos );
    }

    private static class InsertDonanteAsyncTask extends AsyncTask<Donantes, Void, Void> {
        private DonanteDao donanteDao;

        private InsertDonanteAsyncTask( DonanteDao donanteDao ) {
            this.donanteDao = donanteDao;
        }

        @Override
        protected Void doInBackground(Donantes... donantes) {
            donanteDao.Agregar(donantes[0]);
            return null;
        }
    }

    private static class UpdateDonanteAsyncTask extends AsyncTask<Donantes, Void, Void> {
        private DonanteDao donanteDao;

        private UpdateDonanteAsyncTask( DonanteDao donanteDao ) {
            this.donanteDao = donanteDao;
        }

        @Override
        protected Void doInBackground(Donantes... donantes) {
            donanteDao.Modificar(donantes[0]);
            return null;
        }
    }

    public void insert(Solicitudes solicitud) {
        new InsertSolicitudAsyncTask(solicitudesDao).execute(solicitud);
    }

    private static class InsertSolicitudAsyncTask extends AsyncTask<Solicitudes, Void, Void> {
        private SolicitudesDao solicitudesDao;

        private InsertSolicitudAsyncTask( SolicitudesDao solicitudesDao ) {
            this.solicitudesDao = solicitudesDao;
        }

        @Override
        protected Void doInBackground(Solicitudes... solicitudes) {
            solicitudesDao.Agregar(solicitudes[0]);
            return null;
        }
    }

    public LiveData<List<Solicitudes>> getSolicitudes() {
        return solicitudesDao.getAllSolicitudes();
    }

    public LiveData<List<Solicitudes>> getSolicitudesDonante( String cedula ) {
        return  solicitudesDao.getSolicitudDeDonante( cedula );
    }

    public LiveData<List<Solicitudes>> getSolicitudesNotLogueado( String cedula ) {
        return solicitudesDao.getSolicitudesNotLogueado( cedula );
    }
}
