package com.example.yodono2.Entidades.Relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Notificaciones;
import com.example.yodono2.Entidades.Solicitudes;

import java.util.List;

public class NotificacionConSolicitudes {

    // esto nos genera una lista de solicitudes creadas por un donante
    @Embedded
    public Notificaciones notificacion;
    @Relation(
            parentColumn = "idSolicitud",
            entityColumn = "id"
    ) public List<Notificaciones> ListadeNotificaciones;

}
