package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(nullable = false)
    private Integer usua_id;

    @Column(nullable = false)
    private Integer rol_rol_id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    // cuando se serializa el objeto, no se incluye la clave por seguridad 
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clave;

    @Column(nullable = false)
    private String telefono;

    // Constructor
    public Usuario() {
    }

    // getters and setter
    public Integer getUsua_id() {
        return usua_id;
    }

    public void setUsua_id(Integer usua_id) {
        this.usua_id = usua_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRol_rol_id() {
        return rol_rol_id;
    }

    public void setRol_rol_id(Integer rol_rol_id) {
        this.rol_rol_id = rol_rol_id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usua_id=" + usua_id +
                ", rol_rol_id=" + rol_rol_id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
    
    