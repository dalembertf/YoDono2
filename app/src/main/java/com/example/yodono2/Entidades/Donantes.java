package com.example.yodono2.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Creo la entidad(Tabla) Donantes
// M.N: implemento Serializable para poder pasar la instancia como parámetro de un intent
@Entity
public class Donantes implements Serializable {

    @PrimaryKey // Selecciono CI como Primary key
    @NonNull
    private String cedula;
    private String passwd;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private String departamento;
    private String grupo_sanguineo;
    private boolean disponibilidad;

    public Donantes(String cedula, String passwd, String email, String nombre, String apellido, String telefono, String departamento, String grupo_sanguineo ) {
        this.cedula = cedula;
        this.passwd = passwd;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.departamento = departamento;
        this.grupo_sanguineo = grupo_sanguineo;
        this.disponibilidad = true;
    }

    //Getters and Setters

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getGrupo_Sanguineo() {
        return grupo_sanguineo;
    }
    public void setGrupo_Sanguineo(String grupo_Sanguineo) { this.grupo_sanguineo = grupo_Sanguineo; }
    public boolean isDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad( Boolean disponibilidad ) { this.disponibilidad = disponibilidad; }
}

