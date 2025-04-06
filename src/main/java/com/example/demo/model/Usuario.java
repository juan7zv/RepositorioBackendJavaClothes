package com.example.demo.model;

import com.example.demo.model.enums.RolUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    private String idUsuario;

    private String nombre;
    private String email;
    private String numeroDeTelefono;

    private RolUsuario idRol;
    // Clave de acceso del usuario no se entrega en la respuesta al front
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clave;

    // Constructor
    public Usuario(String idUsuario, String nombre, String email, String numeroDeTelefono, RolUsuario idRol, String clave) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.numeroDeTelefono = numeroDeTelefono;
        this.idRol = idRol;
        this.clave = clave;
    }


    // getters y setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public RolUsuario getIdRol() {
        return idRol;
    }

    public void setIdRol(RolUsuario idRol) {
        this.idRol = idRol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}