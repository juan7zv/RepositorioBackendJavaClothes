package com.example.demo.controller;

import com.example.demo.model.Factura;
import com.example.demo.model.Producto;
import com.example.demo.service.FacturaService;
import com.example.demo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Facturas", description = "API para la gestión de facturas")
public class FacturaController {
    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Crear una nueva factura", description = "Crea una nueva factura con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Factura> createFactura(@RequestBody @Parameter(description = "Datos de la factura a crear") Factura factura) {
        Factura newFactura = facturaService.save(factura);
        return new ResponseEntity<>(newFactura, HttpStatus.CREATED);
    }

    // READ (All)
    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Devuelve una lista de todas las facturas en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.findAll();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    // READ (By ID)
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una factura por ID", description = "Devuelve la factura con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Factura> getFacturaById(@PathVariable @Parameter(description = "ID de la factura a obtener") Integer id) {
        Factura factura = facturaService.findById(id);
        if (factura != null) {
            return new ResponseEntity<>(factura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una factura", description = "Actualiza la factura con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Factura> updateFactura(@PathVariable @Parameter(description = "ID de la factura a actualizar") Integer id,
                                                 @RequestBody @Parameter(description = "Datos de la factura a actualizar") Factura factura) {
        Factura updatedFactura = facturaService.update(factura);
        if (updatedFactura != null) {
            return new ResponseEntity<>(updatedFactura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una factura", description = "Elimina la factura con el ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Factura eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Void> deleteFactura(@PathVariable @Parameter(description = "ID de la factura a eliminar") Integer id) {
        Factura factura = facturaService.findById(id);
        if (factura != null) {
            facturaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}