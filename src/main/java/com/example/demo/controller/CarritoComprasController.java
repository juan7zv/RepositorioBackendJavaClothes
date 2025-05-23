package com.example.demo.controller;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.Usuario;
import com.example.demo.service.CarritoComprasService;
import com.example.demo.service.DetalleCarritoService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UsuarioService;
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

@RestController
@RequestMapping("/api/carritoCompras")
@Tag(name = "CarritoCompras", description = "API para la gestión de Carritos de compras. " +
        "Cada Cliente Tiene un único Carrito de Compras.")

public class CarritoComprasController {
    private final JwtService jwtService;
    private final CarritoComprasService carritoComprasService;
    private final DetalleCarritoService detalleCarritoService;
    private final UsuarioService usuarioService;

    @Autowired
    public CarritoComprasController(JwtService jwtService,
                                    CarritoComprasService carritoComprasService,
                                    DetalleCarritoService detalleCarritoService, UsuarioService usuarioService) {
        this.carritoComprasService = carritoComprasService;
        this.detalleCarritoService = detalleCarritoService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    // primer Endpoint get all carritoCompras
    @GetMapping
    @Operation(summary = "Obtener todos los carritos de compras",
            description = "Devuelve una lista de todos los carritos de compras registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carritos de compras obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public ResponseEntity<List<CarritoCompras>> getAllCarritosCompras() {
        List<CarritoCompras> carritos = carritoComprasService.findAll();
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    // segundo Endpoint get carritoCompras por id y usuario
    @GetMapping("/{idCarrito}/{idUsuario}")
    @Operation(summary = "Obtener carrito de compra por ID",
            description = "Devuelve un carrito de compra específico basado en el id del carrito y el usuario logueado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito de compra encontrado"),
            @ApiResponse(responseCode = "404", description = "Carrito de compra no encontrado")})
    public ResponseEntity<CarritoCompras> getCarritoComprasById(
            @PathVariable @Parameter(description = "ID del carrito de compras") Integer idCarrito,
            @PathVariable @Parameter(description = "ID del usuario logueado") Integer idUsuario) {
        CarritoCompras carritoCompras = carritoComprasService.findByIdAndUsuario(idCarrito, idUsuario);
        if (carritoCompras != null) {
            return new ResponseEntity<>(carritoCompras, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // tercer Endpoint get carritoCompras por id de carritoCompras
    @GetMapping("/{id}")
    @Operation(summary = "Obtener carrito de compra por ID",
            description = "Devuelve un carrito de compra específico basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito de compra encontrado"),
            @ApiResponse(responseCode = "404", description = "Carrito de compra no encontrado")})
    public ResponseEntity<CarritoCompras> getCarritoComprasById(
            @PathVariable @Parameter(description = "ID del carrito de compras") Integer id) {

        CarritoCompras carritoCompras = carritoComprasService.findById(id);
        if (carritoCompras != null) {
            return new ResponseEntity<>(carritoCompras, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // cuarto Endpoint post carritoCompras
    @PostMapping
    @Operation(summary = "Crear un nuevo Carrito Compras",
            description = "Crea un nuevo Carrito Compras con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito Compras creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")})

    public ResponseEntity<?> createCarritoCompras(
            @RequestBody
            @Parameter(description = "Datos del Carrito Compras a crear") CarritoCompras carritoCompras,
            @RequestHeader(value = "Authorization", required = false)
            @Parameter(description = "Token de autorización JWT",
                    in = ParameterIn.HEADER, name = "Authorization",
                    example = "Bearer <token>") String authHeader) {
        String token = jwtService.extractToken(authHeader);
        if (token == null || !jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Token JWT inválido o ausente.");
        }

        Integer usuarioId = jwtService.getIdFromToken(token);

        if (usuarioId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "No se pudo extraer el ID del usuario del token.");
        }

        Optional<Usuario> optionalUsuario = usuarioService.findAll().stream()
                .filter(u -> u.getUsua_id().equals(usuarioId))
                .findFirst();

        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Usuario asociado al token no encontrado.");
        }
        Usuario usuario = optionalUsuario.get();
        // Verificar si el usuario ya tiene un carrito de compras
        List<CarritoCompras> carritosUsuario = carritoComprasService.findAll();
        boolean yaTieneCarrito = carritosUsuario.stream()
                .anyMatch(c -> c.getUsuario() != null && c.getUsuario().getUsua_id().equals(usuario.getUsua_id()));
        if (yaTieneCarrito) {
            CarritoCompras carritoExistente = carritosUsuario.stream()
                    .filter(c -> c.getUsuario() != null && c.getUsuario().getUsua_id().equals(usuario.getUsua_id()))
                    .findFirst().orElse(null);
            return ResponseEntity.ok(carritoExistente);
        }
        carritoCompras.setUsuario(carritoCompras.getUsuario() != null ? carritoCompras.getUsuario() : new Usuario());
        carritoCompras.getUsuario().setUsua_id(usuario.getUsua_id());
        CarritoCompras createdCarrito = carritoComprasService.save(carritoCompras);

        return new ResponseEntity<>(createdCarrito, HttpStatus.CREATED);
    }

 /*
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un Carrito Compras", description = "Actualiza los datos de un Carrito Compras existente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Carrito Compras actualizado con éxito"),
			@ApiResponse(responseCode = "404", description = "Carrito Compras no encontrado") })
	public ResponseEntity<CarritoCompras> updateCarritoCompras(
			@PathVariable @Parameter(description = "ID del Carrito Compras") Integer id,
			@RequestBody @Parameter(description = "Datos actualizados del Carrito Compras") DetalleCompraCliente carritoCompras) {
		CarritoCompras existingCarritoCompras = carritoComprasService.findById(id);
		if (existingCarritoCompras != null) {
			detalleCarritoService.save(carritoCompras);
			return new ResponseEntity<>(existingCarritoCompras, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Carrito Compras", description = "Elimina un Carrito Compras basado en su ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Carrito Compras eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Carrito Compras no encontrado")})
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

	/*@GetMapping("/buscar")
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

	}*/
}