package com.example.yodono2.Entidades;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;
import java.util.List;

//Creo la entidad(Tabla) Solicitudes
@Entity
public class Solicitudes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // Datos personales
    private String cedula;
    private String nombre;
    private String apellido;
    private String grupo_sanguineo;

    // Datos de la solicitud
    private String hospital;
    private String fecha_limite;
    private String motivo;
    private String cantidad_donantes;
    private String departamento;

    public Solicitudes(){}
    public Solicitudes(String cedula, String nombre, String apellido, String grupo_sanguineo, String hospital, String fecha_Limite, String motivo, String cantidad_donantes, String departamento ) {

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.grupo_sanguineo = grupo_sanguineo;
        this.hospital = hospital;
        this.fecha_limite = fecha_Limite;
        this.motivo = motivo;
        this.cantidad_donantes = cantidad_donantes;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCantidad_donantes() {
        return cantidad_donantes;
    }

    public void setCantidad_donantes(String cantidad_donantes) {
        this.cantidad_donantes = cantidad_donantes;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String toString() {
        return "Solicitud de " + this.getCedula() + " - " + this.departamento + " - " + this.getGrupo_sanguineo() + " - " + this.getFecha_limite();
    }
}

