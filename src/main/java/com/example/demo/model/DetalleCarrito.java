
package com.example.demo.model;

public class DetalleCarrito {
    private int detalleCarritoId;
    private int cantidad; 
    
    private CarritoCompras carritoCompras;
    private Producto producto;

    public DetalleCarrito(int detalleCarritoId, int cantidad, CarritoCompras carritoCompras, Producto producto) {
        this.detalleCarritoId = detalleCarritoId;
        this.cantidad = cantidad;
        this.carritoCompras = carritoCompras;
        this.producto = producto;
    }

    public int getDetalleCarritoId() {
        return detalleCarritoId;
    }

    public void setDetalleCarritoId(int detalleCarritoId) {
        this.detalleCarritoId = detalleCarritoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
