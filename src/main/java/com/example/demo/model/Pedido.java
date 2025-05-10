package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import com.example.demo.model.enums.EstadosPedido;

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
    @Column(nullable = false)
    private Integer pedi_id;
    
    @Column(nullable = false)
    private Integer usua_id;
    
    @Column(nullable = false)
    private Integer fact_id;
    
    @Column(nullable = false)
    private EstadosPedido estado;
    
    @Column(nullable = false)
    private LocalDate fecha_ped;
    
    @Column(nullable = false)
    private String codigo_comp;
    
    // private ArrayList<DetallePedido> detallesVenta;

    public Pedido() { // Constructor vacío

    }
 
    //getters and setters
    public Integer getPedidoId() {
        return pedi_id;
    }

    public void setPedidoId(Integer pedi_id) {
        this.pedi_id = pedi_id;
    }

    public int getIdCliente() {
        return usua_id;
    }

    public void setIdCliente(int idCliente) {
        this.usua_id = idCliente;
    }

    public EstadosPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadosPedido estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha_ped;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha_ped = fecha;
    }

    public int getIdFactura() {
        return fact_id;
    }

    public void setFactura(int idFactura) {
        this.fact_id = idFactura;
    }

    public String getCodigoCompra() {
        return codigo_comp;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigo_comp = codigoCompra;
    }

	public Integer getPedi_id() {
		return pedi_id;
	}

	public void setPedi_id(Integer pedi_id) {
		this.pedi_id = pedi_id;
	}

	public Integer getUsua_id() {
		return usua_id;
	}

	public void setUsua_id(Integer usua_id) {
		this.usua_id = usua_id;
	}

	public Integer getFact_id() {
		return fact_id;
	}

	public void setFact_id(Integer fact_id) {
		this.fact_id = fact_id;
	}

	public EstadosPedido getEstadoPedido() {
		return estado;
	}

	public void setEstadoPedido(EstadosPedido estado) {
		this.estado = estado;
	}

	public LocalDate getFecha_ped() {
		return fecha_ped;
	}

	public void setFecha_ped(LocalDate fecha_ped) {
		this.fecha_ped = fecha_ped;
	}

	public String getCodigo_comp() {
		return codigo_comp;
	}

	public void setCodigo_comp(String codigo_comp) {
		this.codigo_comp = codigo_comp;
	}
	
    @Override
	public String toString() {
		return "Pedido{" + "pedi_id=" + pedi_id + ", usua_id=" + usua_id + ", fact_id=" + fact_id + ", estado=" + estado + ", fecha_ped=" + fecha_ped + ", codigo_comp=" + codigo_comp + '}';
    }

}
