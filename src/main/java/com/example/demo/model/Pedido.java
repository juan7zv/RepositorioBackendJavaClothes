package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Rossi
 */
public class Pedido {
    private int pedidoId;
    private Factura factura; 
    private Usuario cliente;
    private EstadosPedido estado;
    private LocalDate fecha;
    private String codigoCompra;
    private ArrayList<DetallePedido> detallesVenta;

    public Pedido() {
        this.pedidoId = UUID.randomUUID();
        this.codigoCompra = UUID.randomUUID().toString();
    }
    
    public Pedido(Factura factura, Usuario cliente, EstadosPedido estado, LocalDate fecha, ArrayList<DetallePedido> detallesVenta) {
        this.pedidoId = UUID.randomUUID();
        this.factura = factura;
        this.cliente = cliente;
        this.estado = estado;
        this.fecha = fecha;
        this.codigoCompra = UUID.randomUUID().toString();
        this.detallesVenta = detallesVenta;
    }


    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public EstadosPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadosPedido estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetallePedido> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(ArrayList<DetallePedido> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
}
