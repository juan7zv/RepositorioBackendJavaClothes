package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
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
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Producto> createProducto(@RequestBody @Parameter(description = "Datos del producto a crear") Producto producto) {
        Producto newProducto = productoService.save(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    // READ (All)
    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // READ (By ID)
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> getProductoById(@PathVariable @Parameter(description = "ID del producto") Integer id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> updateProducto(@PathVariable @Parameter(description = "ID del producto") Integer id,
                                                   @RequestBody @Parameter(description = "Datos actualizados del producto") Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setStock(producto.getStock());
            existingProducto.setTalla(producto.getTalla());
            existingProducto.setColor(producto.getColor());
            existingProducto.setMaterial(producto.getMaterial());
            existingProducto.setUrl_imagen(producto.getUrl_imagen());
            Producto updatedProducto = productoService.update(existingProducto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> deleteProducto(@PathVariable @Parameter(description = "ID del producto") Integer id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    // BUSCAR POR FILTROS
    @GetMapping("/buscar")
    @Operation(summary = "Buscar productos por filtros", description = "Busca productos por nombre, precio mínimo y máximo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Producto>> buscarProductos(
            @RequestParam(required = false) @Parameter(description = "Nombre del producto (parcial o completo)") String nombre,
            @RequestParam(required = false) @Parameter(description = "Precio mínimo (opcional)") Double precioMin,
            @RequestParam(required = false) @Parameter(description = "Precio máximo (opcional)") Double precioMax) {
        List<Producto> productos = productoService.buscarPorFiltros(nombre, precioMin, precioMax);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    } */
}