package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_compras")
public class CarritoCompras {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carritoId;
    @OneToOne
    @JoinColumn(name = "Usua_id", nullable = false)
    private Usuario usuario;

    // Constructor
    public CarritoCompras() {

    }

    // Getters and Setters
    public Integer getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Integer carritoId) {
        this.carritoId = carritoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Metodo para mostrar el carrito de compras
    @Override
    public String toString() {
        return "CarritoCompras{" + "carritoId=" + carritoId + ", usuario=" + usuario + '}';
    }
}
