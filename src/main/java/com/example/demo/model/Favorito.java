package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorito")
public class Favorito {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int favoritoId;
	    @OneToOne
	    @JoinColumn(name = "Usua_id", nullable = false)
	    private Usuario usuario; 
	    @OneToOne
	    @JoinColumn(name = "prod_id", nullable = false)
	    private Producto producto; 
	    
	    public Favorito() {
	       
	    }

		public int getFavoritoId() {
			return favoritoId;
	    }

	    public void setFavoritoId(int favoritoId) {
	        this.favoritoId = favoritoId;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(Usuario usuario) {
	        this.usuario = usuario;
	    }

	    public Producto getProducto() {
	        return producto;
	    }

	    public void setProducto(Producto producto) {
	        this.producto = producto;
	    }
}
