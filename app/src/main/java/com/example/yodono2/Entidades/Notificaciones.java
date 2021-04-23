package com.example.yodono2.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Notificaciones implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idSolicitud;
    private String cedula;
    private Boolean enviado;

    public Notificaciones(int idSolicitud, String cedula) {
        this.idSolicitud = idSolicitud;
        this.cedula = cedula;
        this.enviado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }
}
