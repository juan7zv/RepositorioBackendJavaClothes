package com.example.demo.model;

public class Favorito {
	    private int favoritoId;
	    private Usuario usuario; 
	    private Producto producto; 

	    public Favorito(int favoritoId, Usuario usuario, Producto producto) {
	        this.favoritoId = favoritoId;
	        this.usuario = usuario;
	        this.producto = producto;
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
