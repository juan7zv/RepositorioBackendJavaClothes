package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.DetalleCarrito;
import com.example.demo.service.DetalleCarritoService;
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
@RequestMapping("/api/detalleCarrito")
@Tag(name = "DetalleCarrito", description = "API para la gestión del detalle Carrito")

public class DetalleCarritoController {
     private final DetalleCarritoService detalleCarritoService;

    @Autowired
    public DetalleCarritoController(DetalleCarritoService detalleCarritoService) {
        this.detalleCarritoService = detalleCarritoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los detalles del carrito", description = "Devuelve una lista de todos los detalles del carrito registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de detalles del carrito obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<DetalleCarrito>> getAllDetalleCarrito() {
        List<DetalleCarrito> detalleCarrito = detalleCarritoService.findAll();
        return new ResponseEntity<>(detalleCarrito, HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Detalle Carrito por ID", description = "Devuelve un Detalle Carrito específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle Carrito encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle Carrito no encontrado")
    })
    public ResponseEntity<DetalleCarrito> getDetalleCarritoById(@PathVariable @Parameter(description = "ID del Detalle Carrito") int id) {
    	DetalleCarrito detalleCarrito = detalleCarritoService.findById(id);
        if (detalleCarrito != null) {
            return new ResponseEntity<>(detalleCarrito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo Detalle Carrito", description = "Crea un nuevo Detalle Carrito con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle Carrito creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<DetalleCarrito> createDetalleCarrito(@RequestBody @Parameter(description = "Datos del Detalle Carrito a crear") DetalleCarrito detalleCarrito) {
        DetalleCarrito newDetalleCarrito = detalleCarritoService.save(detalleCarrito);
        return new ResponseEntity<>(newDetalleCarrito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Detalle Carrito", description = "Actualiza los datos de un Detalle Carrito existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle Carrito actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Detalle Carrito no encontrado")
    })
    public ResponseEntity<DetalleCarrito> updateDetalleCarrito(@PathVariable @Parameter(description = "ID del Detalle Carrito") int id,
                                                   @RequestBody @Parameter(description = "Datos actualizados del Detalle Carrito") DetalleCarrito detalleCarrito) {
    	DetalleCarrito existingDetalleCarrito = detalleCarritoService.findById(id);
        if (existingDetalleCarrito != null) {
        	detalleCarrito.setDetalleCarritoId(id);
        	DetalleCarrito updatedDetalleCarrito = detalleCarritoService.update(detalleCarrito);
            return new ResponseEntity<>(updatedDetalleCarrito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Detalle Carrito", description = "Elimina un Detalle Carrito basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle Carrito eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Detalle Carrito no encontrado")
    })
    public ResponseEntity<Void> deleteDetalleCarrito(@PathVariable @Parameter(description = "ID del Detalle Carrito") int id) {
    	DetalleCarrito existingDetalleCarrito = detalleCarritoService.findById(id);
        if (existingDetalleCarrito != null) {
        	detalleCarritoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar productos por filtros", description = "Busca Detalle Carrito por id o por cantidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle Carrito encontrados"),
            @ApiResponse(responseCode = "400", description = "Detalle Carrito inválidos")
    })
    public ResponseEntity<List<DetalleCarrito>> buscarDetalleCarrito(
        	@RequestParam(required = false) @Parameter(description = "ID del Detalle Carrito") Integer id,
			@RequestParam(required = false) @Parameter(description = "Cantidad del Detalle Carrito") Integer cantidad) {
        List<DetalleCarrito> detalleCarrito = detalleCarritoService.buscarPorFiltros(id, cantidad);
        return new ResponseEntity<>(detalleCarrito, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Obtener detalle de carrito de compra por USUARIO ", description = "Devuelve un carrito de compra específico basado en el id del carrito y el usuario logueado.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Carrito de compra encontrado"),
            @ApiResponse(responseCode = "404", description = "Carrito de compra no encontrado") })
    public ResponseEntity<List<DetalleCarrito>> getCarritoComprasById(
            @PathVariable @Parameter(description = "ID del usuario logueado") String idUsuario) {

        List<DetalleCarrito> detalleCarrito = detalleCarritoService.findByIdUsuario(idUsuario);
        if (detalleCarrito != null) {
            return new ResponseEntity<>(detalleCarrito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
