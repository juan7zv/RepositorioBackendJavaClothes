package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.enums.EstadosPedido;
import com.example.demo.service.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final CarritoComprasService carritoComprasService;
    private final DetalleCarritoService detalleCarritoService;
    private final DetallePedidoService detallePedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService,
                            JwtService jwtService,
                            UsuarioService usuarioService,
                            CarritoComprasService carritoComprasService,
                            DetalleCarritoService detalleCarritoService,
                            DetallePedidoService detallePedidoService) {
        this.pedidoService = pedidoService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
        this.carritoComprasService = carritoComprasService;
        this.detalleCarritoService = detalleCarritoService;
        this.detallePedidoService = detallePedidoService;
    }


    @PostMapping
    @Operation(summary = "Guardar un pedido",
            description = "Crea un nuevo pedido con los datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<?> createPedido(
            @RequestHeader(value = "Authorization", required = false)
            @Parameter(description = "Token de autorización JWT",
                    in = ParameterIn.HEADER, name = "Authorization",
                    example = "Bearer <token>") String authHeader)  {

        // Validación del token
        String token = jwtService.extractToken(authHeader);
        if (token == null || !jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Token JWT inválido o ausente.");
        }

        Integer usuarioId = jwtService.getIdFromToken(token);
        Optional<Usuario> optionalUsuario = usuarioService.findById(usuarioId);
        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado.");
        }
        // Buscar carrito existente
        Optional<CarritoCompras> carritoExistente =
                carritoComprasService.findByUsuarioId(usuarioId);

        List<DetalleCarrito> listaProductos =
                detalleCarritoService.findByCarritoId(carritoExistente.get().getCarritoId());
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCliente(optionalUsuario.get());
        nuevoPedido.setEstado(EstadosPedido.Realizado);
        nuevoPedido.setFecha(LocalDate.now());
        nuevoPedido.setCodigoCompra("COD-" + LocalDateTime.now() + "-" + usuarioId);
        Pedido newPedido = pedidoService.save(nuevoPedido);

        for (DetalleCarrito detalle : listaProductos) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setProducto(detalle.getProducto());
            detallePedido.setCantidad(detalle.getCantidad());
            detallePedido.setPedido(newPedido);
            detallePedidoService.save(detallePedido);
             }

        return new ResponseEntity<>(newPedido, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista con todos los pedidos existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado con éxito"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Void> deletePedido(@PathVariable @Parameter(description = "ID del pedido") Integer id) {
        Pedido existingPedido = pedidoService.findById(id);
        if (existingPedido != null) {
            pedidoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Devuelve un pedido específico basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> getPedidoById(@PathVariable @Parameter(description = "ID del pedido") Integer id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido", description = "Actualiza los datos de un pedido existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado con éxito"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> updatePedido(@PathVariable @Parameter(description = "ID del pedido") Integer id,
            @RequestBody @Parameter(description = "Datos actualizados del pedido") Pedido pedido) {
        Pedido existingPedido = pedidoService.findById(id);
        if (existingPedido != null) {
            pedido.setPedidoId(id);
            Pedido updatedPedido = pedidoService.update(pedido);
            return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/buscar")
    @Operation(summary = "Buscar pedidos por filtros", description = "Busca pedidos por Id del cliente al que pertenece, estado o fecha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Pedido>> buscarPedido(
            @RequestParam(required = false) @Parameter(description = "Id de cliente (a quien pertenece el pedido)") String idCliente,
            @RequestParam(required = false) @Parameter(description = "Estado (Relizado, disponible, No_Reclamado, Facturado)") EstadosPedido estado,
            @RequestParam(required = false) @Parameter(description = "Fecha") LocalDate fecha) {
        List<Pedido> pedidosFiltrados = pedidoService.buscarPedidos(idCliente, estado, fecha);
        return new ResponseEntity<>(pedidosFiltrados, HttpStatus.OK);
    }
    
    */
}
