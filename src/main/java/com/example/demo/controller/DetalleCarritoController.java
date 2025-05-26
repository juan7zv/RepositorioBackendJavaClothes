package com.example.demo.controller;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.DetalleCarrito;
import com.example.demo.service.CarritoComprasService;
import com.example.demo.service.DetalleCarritoService;
import com.example.demo.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar los detalles del carrito de compras.
 * Proporciona endpoints para crear, actualizar, eliminar y obtener detalles del carrito.
 */
@RestController
@RequestMapping("/api/detalleCarrito")
@Tag(name = "DetalleCarrito", description = "API para la gestión del detalle Carrito")

public class DetalleCarritoController {
    private final JwtService jwtService;
    private final DetalleCarritoService detalleCarritoService;
    private final CarritoComprasService carritoComprasService;

    @Autowired
    public DetalleCarritoController(JwtService jwtService,
                                    DetalleCarritoService detalleCarritoService,
                                    CarritoComprasService carritoComprasService) {

        this.jwtService = jwtService;
        this.detalleCarritoService = detalleCarritoService;
        this.carritoComprasService = carritoComprasService;
    }


        // Obtener los detalles del carrito por usuario
        @GetMapping("/usuario/{usuarioId}")
        @Operation(summary = "Obtener los detalles del carrito por usuario",
                description = "Devuelve una lista de los detalles del carrito asociados a un usuario dado.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista de detalles del carrito obtenida con éxito"),
                @ApiResponse(responseCode = "404", description = "Carrito de compras no encontrado"),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public ResponseEntity<List<?>> getDetalleCarritoByUsuarioId(
                @Parameter(description = "ID del usuario para obtener los detalles del carrito")
                @PathVariable int usuarioId) {
            Optional<CarritoCompras> carrito = carritoComprasService.findByUsuarioId(usuarioId);
            if (carrito == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Collections.emptyList());
            }
            List<DetalleCarrito> detalleCarrito = detalleCarritoService.findByCarritoId(carrito.get().getCarritoId());
            return new ResponseEntity<>(detalleCarrito, HttpStatus.OK);
        }

        // Crear un nuevo detalle de carrito
        @PostMapping
        @Operation(summary = "Crear un nuevo Detalle Carrito",
                description = "Crea un nuevo Detalle Carrito validando el carrito del usuario autenticado " +
                        "y el stock del producto.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Detalle Carrito creado con éxito"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos o sin stock"),
                @ApiResponse(responseCode = "404", description = "Carrito de compras no encontrado")
        })
        public ResponseEntity<?> createDetalleCarrito(
                @RequestBody @Parameter(description = "Datos del Detalle Carrito a crear") DetalleCarrito detalleCarrito,
                @RequestHeader(value = "Authorization", required = false)
                @Parameter(description = "Token de autorización JWT", in = ParameterIn.HEADER, name = "Authorization") String authHeader) {

            // Obtener el id del usuario desde el token
            String token = jwtService.extractToken(authHeader);
            if (token == null || !jwtService.validateJwtToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido o ausente.");
            }
            Integer usuarioId = jwtService.getIdFromToken(token);
            if (usuarioId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo extraer el ID del usuario del token.");
            }

            // Buscar el carrito de compras asociado al usuario
            Optional<CarritoCompras> carrito = carritoComprasService.findByUsuarioId(usuarioId);
            if (carrito == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no tiene un carrito de compras asociado.");
            }

            // Validar si el producto ya está en el carrito
            if (detalleCarritoService.existsByCarritoIdAndProductoId(carrito.get().getCarritoId(), detalleCarrito.getProducto().getProd_id())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mensaje\":\"El producto ya fue agregado al carrito y no se puede volver a agregar.\"}");
            }

            // Validar si el producto tiene stock disponible
            if (detalleCarrito.getProducto() == null || detalleCarrito.getProducto().getProd_id() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mensaje\":\"Producto no especificado.\"}");
            }
            int stockDisponible = detalleCarritoService.obtenerStockProducto(detalleCarrito.getProducto().getProd_id());
            if (detalleCarrito.getCantidad() > stockDisponible) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"mensaje\":\"No hay stock suficiente para el producto.\"}");
            }

            // Asociar el carrito encontrado al detalle
            detalleCarrito.setCarritoCompras(carrito.get());

            DetalleCarrito newDetalleCarrito = detalleCarritoService.save(detalleCarrito);
            return new ResponseEntity<>(newDetalleCarrito, HttpStatus.CREATED);
        }

        // actualiza un detalle del carrito, solo la cantidad del producto
        @PutMapping("/{id}")
        @Operation(summary = "Actualizar la cantidad de un Detalle Carrito",
                description = "Actualiza solo la cantidad de un Detalle Carrito existente.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Cantidad actualizada con éxito"),
                @ApiResponse(responseCode = "404", description = "Detalle Carrito no encontrado")
        })
        public ResponseEntity<DetalleCarrito> updateCantidadDetalleCarrito(
                @PathVariable @Parameter(description = "ID del Detalle Carrito") int id,
                @RequestBody @Parameter(description = "Nueva cantidad") DetalleCarrito detalleCarrito) {
            DetalleCarrito existingDetalleCarrito = detalleCarritoService.findById(id);
            if (existingDetalleCarrito != null) {
                existingDetalleCarrito.setCantidad(detalleCarrito.getCantidad());
                DetalleCarrito updatedDetalleCarrito = detalleCarritoService.update(existingDetalleCarrito);
                return new ResponseEntity<>(updatedDetalleCarrito, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Elimina un detalle del carrito por su ID
        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar un Detalle Carrito",
                description = "Elimina un Detalle Carrito basado en su ID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Detalle Carrito eliminado con éxito"),
                @ApiResponse(responseCode = "404", description = "Detalle Carrito no encontrado")
        })
        public ResponseEntity<Void> deleteDetalleCarrito(
                @PathVariable @Parameter(description = "ID del Detalle Carrito") int id) {
            DetalleCarrito existingDetalleCarrito = detalleCarritoService.findById(id);
            if (existingDetalleCarrito != null) {
                detalleCarritoService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }

