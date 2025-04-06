package com.example.demo.model;

public class CarritoCompras {
    
    private Integer carritoId;
    private Usuario usuario; 

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

    @Override
    public String toString() {
        return "CarritoCompras{" + "carritoId=" + carritoId + ", usuario=" + usuario + '}';
    }
    
    
}
