package com.example.yodono2.Entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
//Creamos la clase DonanteConSolicitudes que va a representar la relacion entre un donantes y sus solicitudes
public class DonanteConSolicitudes {


    //Realizamos una relacion de 1 a N entre Donantes y solicitudes
    // esto nos genera una lista de solicitudes creadas por un donante
    @Embedded public Donantes donante;
    @Relation(
            parentColumn = "cedula",
            entityColumn = "cedula"
    ) public List<Solicitudes> solicitudes_donante;
}
