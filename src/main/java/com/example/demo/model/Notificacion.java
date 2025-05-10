package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion {
	@Id
	@Column(nullable = false)
    private Integer noti_id;
	
	@Column(nullable = false)
	private Integer pedi_id;
	
	@Column(nullable = false)
	private Integer usua_id;
	
	@Column(nullable = false)
    private String mensaje;
	
	@Column(nullable = false)
    private String fecha;
    
	//constructor
    public Notificacion() {
        
    }
    
    //getters and setters

    public Integer noti_id() {
        return noti_id;
    }

    public void setNoti_id(Integer noti_id) {
        this.noti_id = noti_id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getusua_id() {
        return usua_id;
    }

    public void setUsua_id(int clienteId) {
        this.usua_id = clienteId;
    }

    public int getIdPedido() {
        return pedi_id;
    }

    public void setPedido(int idPedido) {
        this.pedi_id = idPedido;
    }

    @Override
    public String toString() {
		return "Notificacion [noti_id=" + noti_id + ", pedi_id=" + pedi_id + ", usua_id=" + usua_id + ", mensaje="
				+ mensaje + ", fecha=" + fecha + "]";
	}
    
}
