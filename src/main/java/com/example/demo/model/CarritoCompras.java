package com.example.demo.model;

import java.util.List;

public class CarritoCompras {

    // ATRIBUTOS
    private Integer carritoId;
    private Usuario usuario;
    private List<DetalleCarrito> detallesCarrito;

    // CONSTRUCTOR
    // No se usa pasa por parametro la lista de detalle, porque solo se usa cuando consulte el carrito, para guardar y actualizar el carrito no se usan estas listas
    public CarritoCompras(Integer carritoId, Usuario usuario) {
        this.carritoId = carritoId;
        this.usuario = usuario;
    }

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

    public List<DetalleCarrito> getDetalleCarrito() {
        return detallesCarrito;
    }
    public void setDetalleCarrito(List<DetalleCarrito> detalleCarrito) {
        this.detallesCarrito = detalleCarrito;
    }

    @Override
    public String toString() {
        return "CarritoCompras{" + "carritoId=" + carritoId + ", usuario=" + usuario + '}';
    }
    
    
}
