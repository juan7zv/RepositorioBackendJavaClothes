package com.example.demo.model;

import java.util.UUID;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private int edad;

    public Usuario() {
        this.id = UUID.randomUUID().toString();
    }

    public Usuario(String nombre, String email, int edad) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}