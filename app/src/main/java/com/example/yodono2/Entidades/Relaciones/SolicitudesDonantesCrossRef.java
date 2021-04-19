package com.example.yodono2.Entidades.Relaciones;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Solicitudes;

import java.util.List;

public class SolicitudesDonantesCrossRef {

    @Embedded
    public Solicitudes solicitud;
    @Relation(
            parentColumn = "id",
            entity = Donantes.class,
            entityColumn = "cedula",
            associateBy = @Junction( SolicitudConDonantes.class)
    )
    public List<Solicitudes> solicitudesList;
}
