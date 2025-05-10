package com.example.demo.model;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Rossi
 */

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
	
	@Id
	@Column(nullable = false)
    private int detalleId;
	
	@Column(nullable = false)
    private int cantidad;
	
	@Column(nullable = false)
    private int pedi_id;
	
	@Column(nullable = false)
    private int prod_id; 

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

    public int getIdPedido() {
        return pedi_id;
    }

    public void setIdPedido(int idPedido) {
        this.pedi_id = idPedido;
    }

    public int getIdProducto() {
        return prod_id;
    }

    public void setProducto(int productoId) {
        this.pedi_id = productoId;
    }
}
