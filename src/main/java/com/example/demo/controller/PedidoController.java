package com.example.demo.controller;

import com.example.demo.model.Pedido;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
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

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Guardar un pedido", description = "Crea un nuevo pedido con los datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Pedido> createPedido(@RequestBody @Parameter(description = "Datos del pedido a crear") Pedido pedido) {
        Pedido newPedido = pedidoService.save(pedido);
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
    public ResponseEntity<Void> deletePedido(@PathVariable @Parameter(description = "ID del pedido") String id) {
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
    public ResponseEntity<Pedido> getPedidoById(@PathVariable @Parameter(description = "ID del pedido") String id) {
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
    public ResponseEntity<Pedido> updatePedido(@PathVariable @Parameter(description = "ID del pedido") String id,
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

    @GetMapping("/buscar")
    @Operation(summary = "Buscar pedidos por filtros", description = "Busca pedidos por Id del cliente al que pertenece, estado o fecha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Pedido>> buscarPedido(
            @RequestParam(required = false) @Parameter(description = "Id de cliente (a quien pertenece el pedido)") String idCliente,
            @RequestParam(required = false) @Parameter(description = "Estado (Relizado, disponible, No_Reclamado, Facturado)") EstadoPedido estado,
            @RequestParam(required = false) @Parameter(description = "Fecha") LocalDate fecha) {
        List<Pedido> pedidosFiltrados = pedidoService.buscarPorFiltros(idCliente, estado, fecha);
        return new ResponseEntity<>(pedidosFiltrados, HttpStatus.OK);
    }
}
