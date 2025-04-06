package com.example.demo.controller;

import com.example.demo.model.CarritoCompras;
import com.example.demo.service.CarritoComprasService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/carritoCompras")
@Tag(name = "CarritoCompras", description = "API para la gestión de Carrito de compras")
public class CarritoComprasController {
	private final CarritoComprasService carritoComprasService;

	@Autowired
	public CarritoComprasController(CarritoComprasService carritoComprasService) {
		this.carritoComprasService = carritoComprasService;
	}

	@GetMapping
	@Operation(summary = "Obtener todos los carritos de compras", description = "Devuelve una lista de todos los carritos de compras registrados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de carritos de compras obtenida con éxito"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor") })
	public ResponseEntity<List<CarritoCompras>> getAllCarritosCompras() {
		List<CarritoCompras> carritos = carritoComprasService.findAll();
		return new ResponseEntity<>(carritos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener carrito de compra por ID", description = "Devuelve un carrito de compra específico basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Carrito de compra encontrado"),
			@ApiResponse(responseCode = "404", description = "Carrito de compra no encontrado") })
	public ResponseEntity<CarritoCompras> getCarritoComprasById(
			@PathVariable @Parameter(description = "ID del carrito de compras") Integer id) {

		CarritoCompras carritoCompras = carritoComprasService.findById(id);
		if (carritoCompras != null) {
			return new ResponseEntity<>(carritoCompras, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo Carrito Compras", description = "Crea un nuevo Carrito Compras con los datos proporcionados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Carrito Compras creado con éxito"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	public ResponseEntity<CarritoCompras> createCarritoCompras(
			@RequestBody @Parameter(description = "Datos del Carrito Compras a crear") CarritoCompras carritoCompras) {
		CarritoCompras createdCarrito = carritoComprasService.save(carritoCompras);
		return new ResponseEntity<>(createdCarrito, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un Carrito Compras", description = "Actualiza los datos de un Carrito Compras existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Carrito Compras actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Carrito Compras no encontrado") })
	public ResponseEntity<CarritoCompras> updateCarritoCompras(
			@PathVariable @Parameter(description = "ID del Carrito Compras") Integer id,
			@RequestBody @Parameter(description = "Datos actualizados del Carrito Compras") CarritoCompras carritoCompras) {
		CarritoCompras existingCarritoCompras = carritoComprasService.findById(id);
		if (existingCarritoCompras != null) {
			carritoCompras.setCarritoId(id);
			CarritoCompras updatedCarrito = carritoComprasService.update(carritoCompras);
			return new ResponseEntity<>(updatedCarrito, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un Carrito Compras", description = "Elimina un Carrito Compras basado en su ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Carrito Compras eliminado con éxito"),
			@ApiResponse(responseCode = "404", description = "Carrito Compras no encontrado") })
	public ResponseEntity<Void> deleteCarritoCompras(
			@PathVariable @Parameter(description = "ID del Carrito Compras") Integer id) {
		CarritoCompras existingCarritoCompras = carritoComprasService.findById(id);
		if (existingCarritoCompras != null) {
			carritoComprasService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscar")
	@Operation(summary = "Buscar productos por filtros", description = "Busca Carrito Compras por id del carro o el id del usuario.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Carrito Compras encontrados"),
			@ApiResponse(responseCode = "400", description = "Parámetros inválidos") })
	public ResponseEntity<List<CarritoCompras>> buscarCarritoCompras(
			@RequestParam(required = false) @Parameter(description = "ID del carrito de compras") Integer carritoId,
			@RequestParam(required = false) @Parameter(description = "ID del usuario") String usuario) {
		{
			List<CarritoCompras> carritoCompras = carritoComprasService.buscarPorFiltros(carritoId, usuario);
			return new ResponseEntity<>(carritoCompras, HttpStatus.OK);
		}

	}
}
