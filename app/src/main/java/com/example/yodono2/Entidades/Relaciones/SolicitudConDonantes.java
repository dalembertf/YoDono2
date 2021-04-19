package com.example.yodono2.Entidades.Relaciones;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity( primaryKeys = { "id", "cedula" })
public class SolicitudConDonantes {
    @NonNull
    public Integer id;
    @NonNull public String cedula;

    public SolicitudConDonantes( Integer id, String cedula ) {
        this.id = id;
        this.cedula = cedula;
    }
}
