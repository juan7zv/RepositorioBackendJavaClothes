package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import com.example.demo.model.enums.EstadosPedido;

/**
 *
 * @author Rossi
 */
public class Pedido {

    private Integer pedidoId;
    private Factura factura;
    private Usuario cliente;
    private EstadosPedido estado;
    private LocalDate fecha;
    private String codigoCompra;
    private ArrayList<DetallePedido> detallesVenta;

    public Pedido() {

    }

    public Pedido(Integer pedidoId, Factura factura, Usuario cliente, EstadosPedido estado, LocalDate fecha,
        String codigoCompra, ArrayList<DetallePedido> detallesVenta) {
        this.pedidoId = pedidoId;
        this.factura = factura;
        this.cliente = cliente;
        this.estado = estado;
        this.fecha = fecha;
        this.codigoCompra = codigoCompra;
        this.detallesVenta = detallesVenta;
    }

    public void agregarDetalle(DetallePedido detalle) {
        detallesVenta.add(detalle);
    }

    public void eliminarDetalle(DetallePedido detalle) {
        detallesVenta.remove(detalle);
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
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

    @Override
    public String toString() {
        return "Pedido [pedidoId=" + pedidoId + ", cliente=" + cliente + ", fecha=" + fecha + ", codigoCompra="
                + codigoCompra + ", detallesVenta=" + detallesVenta + "]";
    }

}
