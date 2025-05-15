package com.example.demo.controller;
import java.util.List;

import com.example.demo.model.Factura;
import com.example.demo.model.Favorito;
import com.example.demo.service.FavoritoService;
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

    @PostMapping
    @Operation(summary = "Crear un nuevo Favorito", description = "Crea un nuevo Favorito con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorito creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Favorito> createFavorito(@RequestBody @Parameter(description = "Datos del Favorito a crear") Favorito favorito) {
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
        if (existingFavorito!= null) {
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

    /* @GetMapping("/buscar")
    @Operation(summary = "Buscar Favoritos por filtros", description = "Busca Favoritos por su id , ususario o producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favoritos encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
   

	public ResponseEntity<List<Favorito>> buscarFavoritos(
			@RequestParam(required = false) @Parameter(description = "ID del Favorito") Integer id,
			@RequestParam(required = false) @Parameter(description = "ID del Usuario") Integer usuarioId,
			@RequestParam(required = false) @Parameter(description = "ID del Producto") Integer productoId) {
		List<Favorito> favoritos = favoritoService.buscarFavoritos(id, usuarioId, productoId);
		return new ResponseEntity<>(favoritos, HttpStatus.OK);
}*/
}
