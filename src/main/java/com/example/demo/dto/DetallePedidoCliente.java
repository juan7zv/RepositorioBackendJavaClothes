package com.example.demo.dto;

import com.example.demo.model.CarritoCompras;

public class DetallePedidoCliente {
    // ATRIBUTOS
    private int detalleId;
    private int cantidad;
    private int pedidoId;
    private String productoNombre;
    private double precioUnitario;
    private String fechaPedido;

    // CONSTRUCTORES
    public DetallePedidoCliente() {
    }

    public DetallePedidoCliente(int detalleId, int cantidad, int pedidoId, String productoNombre, double precioUnitario, String fechaPedido) {
        this.detalleId = detalleId;
        this.cantidad = cantidad;
        this.pedidoId = pedidoId;
        this.productoNombre = productoNombre;
        this.precioUnitario = precioUnitario;
        this.fechaPedido = fechaPedido;
    }

    // GETTERS Y SETTERS
    public int getDetalleId() {
        return detalleId;
    }
    public void setDetalleId(int detalleId) {
        this.detalleId = detalleId;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
    public String getProductoNombre() {
        return productoNombre;
    }
    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public String getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

}
