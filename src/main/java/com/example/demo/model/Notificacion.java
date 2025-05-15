package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(auto-incremental)
	@Column(name = "noti_id", nullable = false)
    private Integer notiId;
	
	@OneToOne
	@JoinColumn(name = "pedi_id", nullable = false) //
	private Pedido pedido;
	
	@ManyToOne //porque pueden haber múltiples notificaciones por user
	@JoinColumn(name = "usua_id", nullable = false)
	private Usuario usuario;
	
	@Column(name = "mensaje", nullable = false)
    private String mensaje;
	
	@Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
	//constructor
    public Notificacion() {
        
    }
    
    //getters and setters

    public Integer getNotiId() {
        return notiId;
    }

    public void setNotiId(Integer noti_id) {
        this.notiId = noti_id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario user) {
        this.usuario = user;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
		return "Notificacion [noti_id=" + notiId + ", mensaje=" + mensaje + ", fecha=" + fecha + ", usuario=" + usuario
				+ ", pedido=" + pedido + "]";
	}
    
}
