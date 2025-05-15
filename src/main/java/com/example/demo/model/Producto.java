package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prod_id;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String url_imagen;

    // Constructor por defecto (necesario para JPA y Jackson)
    public Producto() {
    }

    // getters y setters


    public Integer getProd_id() {
        return prod_id;
    }

    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "prod_id=" + prod_id +
                ", stock=" + stock +
                ", precio=" + precio +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", talla='" + talla + '\'' +
                ", material='" + material + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tipo='" + tipo + '\'' +
                ", url_imagen='" + url_imagen + '\'' +
                '}';
    }
}