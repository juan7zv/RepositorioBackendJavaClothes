package com.example.demo.controller;

import com.example.demo.model.Favorito;
import com.example.demo.service.FavoritoService;
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
@RequestMapping("/api/favorito")
@Tag(name = "Favorito", description = "API para la gestión de favorito")
public class FavoritoController {
    private final FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Favoritos", description = "Devuelve una lista de todos los Favoritos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Favorito obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Favorito>> getAllFavoritos() {
        List<Favorito> favoritos = favoritoService.findAll();
        return new ResponseEntity<>(favoritos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Favorito por ID", description = "Devuelve un Favorito específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorito encontrado"),
            @ApiResponse(responseCode = "404", description = "Favorito no encontrado")
    })
    public ResponseEntity<Favorito> getFavoritoById(@PathVariable @Parameter(description = "ID del Favorito") int id) {
        Favorito favorito = favoritoService.findById(id);
        if (favorito != null) {
            return new ResponseEntity<>(favorito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // endopoint para obtener favoritos por ID de cliente
    @GetMapping("/cliente/{idCliente}")
    @Operation(summary = "Obtener Favoritos por ID de Cliente",
            description = "Devuelve todos los favoritos asociados a un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favoritos encontrados (Puede estar vacía)" ),
    })
    public ResponseEntity<List<Favorito>> getFavoritosByClienteId(
            @Parameter(description = "ID del Cliente")
            @PathVariable int idCliente) {
        List<Favorito> favoritos = favoritoService.findByCliente(idCliente);
        return new ResponseEntity<>(favoritos, HttpStatus.OK);

    }

    // endopoint para crear un nuevo favorito
    @PostMapping
    @Operation(summary = "Crear un nuevo Favorito", description = "Crea un nuevo Favorito con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorito creado con éxito"),
            @ApiResponse(responseCode = "409", description = "El producto ya está en favoritos"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<?> createFavorito(
            @Parameter(description = "Datos del Favorito a crear")
            @RequestBody Favorito favorito) {
        // Verificar si ya existe ese producto en favoritos de ese cliente
        if(favoritoService.findByProductoAndUsuario(
                favorito.getProducto().getProd_id(), favorito.getUsuario().getUsua_id()).isPresent()) {
            return new ResponseEntity<>("El producto ya está en favoritos", HttpStatus.CONFLICT);
        }
        Favorito newFavorito = favoritoService.save(favorito);
        return new ResponseEntity<>(newFavorito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Favorito", description = "Actualiza los datos de un Favorito existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorito actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Favorito no encontrado")
    })
    public ResponseEntity<Favorito> updateProducto(@PathVariable @Parameter(description = "ID del Favorito") int id,
                                                   @RequestBody @Parameter(description = "Datos actualizados del Favorito") Favorito favorito) {
        Favorito existingFavorito = favoritoService.findById(id);
        if (existingFavorito != null) {
            favorito.setFavoritoId(id);
            Favorito updatedFavorito = favoritoService.update(favorito);
            return new ResponseEntity<>(updatedFavorito, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Favorito", description = "Elimina un Favorito basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Favorito eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Favorito no encontrado")
    })
    public ResponseEntity<Void> deleteFavorito(@PathVariable @Parameter(description = "ID del Favorito") int id) {
        Favorito existingFavorito = favoritoService.findById(id);
        if (existingFavorito != null) {
            favoritoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
