package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detalle_carrito_id;

	@Column(nullable = false)
	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "carritoCompras_id", nullable = false)
	private CarritoCompras carritoCompras;

	@ManyToOne
	@JoinColumn(name = "prod_id", nullable = false)
	private Producto producto;

	public DetalleCarrito() {

	}

	public int getDetalle_carrito_id() {
		return detalle_carrito_id;
	}

	public void setDetalle_carrito_id(int detalleCarritoId) {
		this.detalle_carrito_id = detalleCarritoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public CarritoCompras getCarritoCompras() {
		return carritoCompras;
	}

	public void setCarritoCompras(CarritoCompras carritoCompras) {
		this.carritoCompras = carritoCompras;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
