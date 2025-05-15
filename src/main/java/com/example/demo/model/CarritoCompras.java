package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "carritoCompras")
public class CarritoCompras {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carritoId;
	@OneToOne
	@JoinColumn(name = "Usua_id", nullable = false)
    private Usuario usuario;

    public CarritoCompras() {
     
    }

    public Integer getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Integer carritoId) {
        this.carritoId = carritoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "CarritoCompras{" + "carritoId=" + carritoId + ", usuario=" + usuario + '}';
    }
    
    
}
