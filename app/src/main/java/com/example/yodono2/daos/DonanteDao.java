package com.example.yodono2.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.example.yodono2.Entidades.DonanteConSolicitudes;
import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Solicitudes;

//Representa la interface por la cual realizamos las interacciones con la BD
@Dao
public interface DonanteDao {

    @Insert
    void Agregar(Donantes donante);

    @Update
    void Modificar(Donantes donante);

    @Query("SELECT * FROM Donantes WHERE cedula = :ci")
    Donantes buscarDonante(String ci);

    @Query("SELECT * FROM Donantes WHERE cedula != :ci")
    LiveData<List<Donantes>> buscarDonantesNotLogged(String ci);

    @Query("SELECT * FROM Donantes")
    LiveData<List<Donantes>> getAllDonanantes();

    @Query("SELECT * FROM Donantes WHERE cedula = :ci and passwd = :passwd")
    Donantes getDonante( String ci, String passwd );

    @Insert
    void agregarSolicitudDonante(Solicitudes solicitud);

    // query para retornar solicitudes de un donante
    @Transaction
    @Query("SELECT * FROM Donantes WHERE cedula = :cedula")
    LiveData<List<DonanteConSolicitudes>> getSolicitudesDonante(String cedula);

    // query para retornar donantes por depto + grupo
    @Transaction
    @Query("SELECT * FROM Donantes WHERE departamento = :departamento AND grupo_sanguineo = :grupo_sanguineo and cedula != :cedula")
    LiveData<List<Donantes>> getDonantesPorFiltros( String departamento, String grupo_sanguineo, String cedula );
}
