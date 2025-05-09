package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import com.example.demo.model.enums.EstadosPedido;
import jakarta.persistence.*;

/**
 *
 * @author Rossi
 */

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedi_id;

    @OneToOne
    @JoinColumn(name = "fact_id", nullable = false)
    private Factura factura;

    @OneToOne
    @JoinColumn(name = "usua_id", nullable = false)
    private Usuario cliente;


    private EstadosPedido estado;
    private LocalDate fecha;
    private String codigoCompra;
    private ArrayList<DetallePedido> detallesVenta;

    public Pedido() {

    }

    public Pedido(Integer pedidoId, Factura factura, Usuario cliente, EstadosPedido estado, LocalDate fecha,
        String codigoCompra, ArrayList<DetallePedido> detallesVenta) {
        this.pedi_id = pedidoId;
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

    public Integer getPedi_id() {
        return pedi_id;
    }

    public void setPedi_id(Integer pedi_id) {
        this.pedi_id = pedi_id;
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
        return "Pedido [pedidoId=" + pedi_id + ", cliente=" + cliente + ", fecha=" + fecha + ", codigoCompra="
                + codigoCompra + ", detallesVenta=" + detallesVenta + "]";
    }

}
