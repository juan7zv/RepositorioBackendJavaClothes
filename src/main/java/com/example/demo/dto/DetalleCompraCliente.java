package com.example.demo.dto;

import com.example.demo.model.CarritoCompras;

public class DetalleCompraCliente {
    // ATRIBUTOS
    private CarritoCompras carritoCompras;
    private Integer idProducto;

    // CONSTRUCTOR
    public DetalleCompraCliente(CarritoCompras carritoCompras, Integer idProducto) {
        this.carritoCompras = carritoCompras;
        this.idProducto = idProducto;
    }

    // GETTERS AND SETTERS

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
