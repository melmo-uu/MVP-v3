package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marce
 */

//Claramente esto es un ejemplo por que no se si ta bien FASDFASD
public class Usuario {
    
    private int ID;
    private String Nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String Email;
    private String Contrasena;
    private boolean Rol; 
    private String nombrecompleto;
    private LocalDateTime fechaCreacion;
    private boolean activo;
    private String Rut;

    public Usuario(int ID, String Nombre, String ApellidoP, String ApellidoM, String Email, String Contrasena, boolean Rol, String nombrecompleto, LocalDateTime fechaCreacion, boolean activo, String Rut) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Email = Email;
        this.Contrasena = Contrasena;
        this.Rol = Rol;
        this.nombrecompleto = nombrecompleto;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.Rut = Rut;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String ApellidoP) {
        this.ApellidoP = ApellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String ApellidoM) {
        this.ApellidoM = ApellidoM;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public boolean isRol() {
        return Rol;
    }

    public void setRol(boolean Rol) {
        this.Rol = Rol;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public String getFechaCreacionFormateada() {
        if (fechaCreacion == null) return "";
        return fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    
}
