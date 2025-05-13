package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "detallle_carrito")
public class DetalleCarrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detalleCarritoId;
	@Column(nullable = false)
	private int cantidad;
	@OneToOne
	@JoinColumn(name = "carritoCompras_id", nullable = false)
	private CarritoCompras carritoCompras;
	@OneToOne
	@JoinColumn(name = "prod_id", nullable = false)
	private Producto producto;

	public DetalleCarrito() {

	}

	public int getDetalleCarritoId() {
		return detalleCarritoId;
	}

	public void setDetalleCarritoId(int detalleCarritoId) {
		this.detalleCarritoId = detalleCarritoId;
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
