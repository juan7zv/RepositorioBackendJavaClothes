package com.example.demo.model;

import java.util.UUID;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
        this.id = UUID.randomUUID().toString();
    }

    public Producto(String nombre, double precio, int stock) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}