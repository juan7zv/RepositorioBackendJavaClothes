package com.example.demo.model;

import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

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
	
	@ManyToOne // (foreing key)
    @JoinColumn(name = "pedi_id", nullable = false)
    private Pedido pedido;
	
	@ManyToOne // (foreing key)
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
