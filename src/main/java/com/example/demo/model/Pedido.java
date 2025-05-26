package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import com.example.demo.model.enums.EstadosPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Rossi
 */

@Entity
@Table(name = "pedido")
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera automaticamente el id (auto-incremental)
    @Column(name = "pedi_id")
    private Integer pediId;

    @OneToOne //Cuando una entidad está asociada con exactamente una instancia de otra entidad (foreing key)
    @JoinColumn(name = "fact_id", nullable = true)
    private Factura factura;

    @ManyToOne // (foreing key)
    @JoinColumn(name = "usua_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadosPedido estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ped", nullable = false)
    private LocalDate fechaPedido;
    
    @Column(name = "codigo_comp", nullable = false)
    private String codigoCompra;


    public Pedido() { // Constructor vacío

    }
 
    //getters and setters
    public Integer getPedidoId() {
        return pediId;
    }

    public void setPedidoId(Integer pedi_id) {
        this.pediId = pedi_id;
    }

    public Usuario getCliente() {
        return usuario;
    }

    public void setCliente(Usuario user) {
        this.usuario = user;
    }

    public EstadosPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadosPedido estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fechaPedido;
    }

    public void setFecha(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
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
    	return "Pedido [pedi_id=" + pediId + ", factura=" + factura + ", usuario=" + usuario + ", estado=" + estado
    							+ ", fechaPedido=" + fechaPedido + ", codigoCompra=" + codigoCompra + "]";
    }

}
