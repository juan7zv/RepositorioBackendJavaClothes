package com.example.demo.controller;

import com.example.demo.dto.DetallePedidoCliente;
import com.example.demo.model.DetallePedido;
import com.example.demo.model.Pedido;
import com.example.demo.service.DetallePedidoService;
import com.example.demo.service.JwtService;
import com.example.demo.service.PedidoService;
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
import java.util.Optional;
import java.util.ArrayList;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/detallePedido")
@Tag(name = "Detalles de pedido", description = "API para la gestión de detalles de pedidos")

public class DetallePedidoController {

    private final JwtService jwtService;
    private final DetallePedidoService detallePedidoService;
    private final PedidoService pedidoService;

    @Autowired
    public DetallePedidoController(
            JwtService jwtService,
            DetallePedidoService detallePedidoService,
            PedidoService pedidoService) {

        this.jwtService = jwtService;
        this.detallePedidoService = detallePedidoService;
        this.pedidoService = pedidoService;
    }

    // obtener los detalles de pedidos por usuario
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener detalles de pedidos por usuario",
            description = "Devuelve una lista de detalles de pedidos asociados a un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de pedidos encontrados"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin pedidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> getDetallesPedidoByUsuarioId(
            @Parameter(description = "ID del usuario")
            @PathVariable Integer usuarioId) {
        try {
            // 1. Buscar todos los pedidos del usuario
            List<Pedido> pedidosUsuario = pedidoService.findAllByUsuarioId(usuarioId);
            if (pedidosUsuario.isEmpty()) {
                return new ResponseEntity<>("No se encontraron pedidos para este usuario", HttpStatus.NOT_FOUND);
            }

            // 2. Obtener todos los detalles de todos los pedidos
            List<DetallePedidoCliente> todosLosDetalles = new ArrayList<>();
            for (Pedido pedido : pedidosUsuario) {
                List<DetallePedido> detallesPedido = detallePedidoService.findByPedido(pedido);
                if (detallesPedido != null && !detallesPedido.isEmpty()) {
                    for (DetallePedido detalle : detallesPedido) {
                        DetallePedidoCliente detalleCliente = new DetallePedidoCliente();
                        detalleCliente.setDetalleId(detalle.getDetalleId());
                        detalleCliente.setProductoNombre(detalle.getProducto().getNombre());
                        detalleCliente.setCantidad(detalle.getCantidad());
                        detalleCliente.setPedidoId(pedido.getPedidoId());
                        detalleCliente.setFechaPedido(pedido.getFecha().toString());
                        detalleCliente.setPrecioUnitario(detalle.getProducto().getPrecio());
                        todosLosDetalles.add(detalleCliente);
                    }
                }
            }

            if (todosLosDetalles.isEmpty()) {
                return new ResponseEntity<>("No se encontraron detalles de pedidos para este usuario", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(todosLosDetalles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Crear un nuevo detalle de pedido
    @PostMapping
    @Operation(summary = "Guardar el detalle de un pedido", description = "Crea un detalle de pedido con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de pedido creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<DetallePedido> createDetallePedido(
            @RequestBody @Parameter(description = "Datos del detalle de pedido a crear") DetallePedido detallePedido) {
        DetallePedido newDetallePedido = detallePedidoService.save(detallePedido);
        return new ResponseEntity<>(newDetallePedido, HttpStatus.CREATED);
    }

    // Endpoint para obtener detalles de un pedido por el id de pedido
    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles del pedido",
            description = "Devuelve todos los detalles asociados a un pedido específico por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles encontrados"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<?> getDetallesByPedidoId(@PathVariable @Parameter(description = "ID del pedido") Integer id) {
        try {
            // 1. Buscar el pedido por ID
            Pedido pedido = pedidoService.findById(id);
            if (pedido == null) {
                return new ResponseEntity<>("No se encontró el pedido con ID: " + id, HttpStatus.NOT_FOUND);
            }

            // 2. Buscar los detalles por pedido
            List<DetallePedido> detalles = detallePedidoService.findByPedido(pedido);
            if (detalles == null || detalles.isEmpty()) {
                return new ResponseEntity<>("No se encontraron detalles para el pedido con ID: " + id, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(detalles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 /*   @PutMapping("/{id}")
    @Operation(summary = "Actualizar el detalle de un pedido", description = "Actualiza los detalles de un pedido existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle del pedido actualizado con éxito"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> updateDetallePedido(@PathVariable @Parameter(description = "ID del pedido") Integer id,
            @RequestBody @Parameter(description = "Detalles actualizados del pedido") Pedido pedido) {
        // 1. Buscar el pedido por ID
        Pedido pedidoActualizar = pedidoService.findById(id);
        if (pedidoActualizar != null) {
            // 2. Eliminar los detalles existentes del pedido
            List<DetallePedido> existingDetalles = detallePedidoService.findByPedido(pedidoActualizar);
            if (existingDetalles != null) {
                for (DetallePedido detalle : existingDetalles) {
                    detallePedidoService.deleteById(id);
                }
            }
            // 3. Actualizar el pedido con los nuevos detalles
            pedido.setPedi_id(id);
            Pedido updatedPedido = pedidoService.update(pedido);
            return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    } */
}

//    @GetMapping
//    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista con todos los pedidos existentes.")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con éxito"),
//        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
//    })
//    public ResponseEntity<List<Pedido>> getAllPedidos() {
//        List<Pedido> pedidos = pedidoService.findAll();
//        return new ResponseEntity<>(pedidos, HttpStatus.OK);
//    }
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido basado en su ID.")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "204", description = "Pedido eliminado con éxito"),
//        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
//    })
//    public ResponseEntity<Void> deletePedido(@PathVariable @Parameter(description = "ID del pedido") String id) {
//        Pedido existingPedido = pedidoService.findById(id);
//        if (existingPedido != null) {
//            pedidoService.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

