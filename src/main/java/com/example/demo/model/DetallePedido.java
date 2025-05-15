package com.example.demo.model;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Rossi
 */

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //(auto-incremental)
	@Column(name = "detalle_id", nullable = false)
    private int detalleId;
	
	@Column(name = "cantidad", nullable = false)
    private int cantidad;
	
	@OneToOne // (foreing key)
    @JoinColumn(name = "pedi_id", nullable = false)
    private Pedido pedido;
	
	@OneToOne // (foreing key)
	@JoinColumn(name = "prod_id", nullable = false)
    private Producto producto; 

    public DetallePedido() {
       
    }

    //getters and setters

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
