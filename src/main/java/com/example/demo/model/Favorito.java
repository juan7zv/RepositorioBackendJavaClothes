package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorito")
public class Favorito {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int favoritoId;

	    @ManyToOne
	    @JoinColumn(name = "Usua_id", nullable = false)
	    private Usuario usuario;

	    @ManyToOne
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
