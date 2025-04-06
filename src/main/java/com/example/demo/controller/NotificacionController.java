package com.example.demo.controller;

import com.example.demo.model.Notificacion;
import com.example.demo.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/notificaciones")
@Tag(name = "Notificaciones", description = "API para la gestión de notificaciones enviadas al cliente")
public class NotificacionController {

        private final NotificacionService notificacionService;

        @Autowired
        public NotificacionController(NotificacionService notificacionService) {
            this.notificacionService = notificacionService;
        }

        @PostMapping
        @Operation(summary = "Guardar notificación", description = "Crea una nueva notificación con los datos proporcionados.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        })
        public ResponseEntity<Notificacion> createNotificacion(@RequestBody @Parameter(description = "Datos de la notificación a crear") Notificacion notificacion) {
            Notificacion newPedido = notificacionService.save(notificacion);
            return new ResponseEntity<>(newPedido, HttpStatus.CREATED);
        }

        @GetMapping
        @Operation(summary = "Obtener todas las notificaciones", description = "Devuelve una lista de todas las notificaciones existentes.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
        public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
            List<Notificacion> notificaciones = notificacionService.findAll();
            return new ResponseEntity<>(notificaciones, HttpStatus.OK);
        }

        @GetMapping("/cliente/{id}")
        @Operation(summary = "Obtener notificación por ID", description = "Devuelve una notificación específica basada en su ID.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación encontrado"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrado")
        })
        public ResponseEntity<Notificacion> getNotificacionById(@PathVariable @Parameter(description = "ID de la notificacion") String id) {
            Notificacion notificacion = notificacionService.findById(id);
            if (notificacion != null) {
                return new ResponseEntity<>(notificacion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar una notificación", description = "Elimina un pedido basado en su ID.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificación eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrado")
        })
        public ResponseEntity<Void> deleteNotificacion(@PathVariable @Parameter(description = "ID de la notificación") String id) {
            Notificacion existingNotificacion = notificacionService.findById(id);
            if (existingNotificacion != null) {
                notificacionService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/{id}")
        @Operation(summary = "Actualizar una notificacion", description = "Actualiza los datos de un notificacion existente.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrado")
        })
        public ResponseEntity<Notificacion> updateNotificacion(@PathVariable @Parameter(description = "ID de la Notificación") String id,
                @RequestBody @Parameter(description = "Datos actualizados de la notificación") Notificacion noti) {
            Notificacion existingNotificacion = notificacionService.findById(id);
            if (existingNotificacion != null) {
                noti.setIdNotificacion(id);
                Notificacion updatedNotificacion = notificacionService.update(noti);
                return new ResponseEntity<>(updatedNotificacion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/cliente/{id}")
        @Operation(summary = "Obtener notificación por cliente", description = "Devuelve una lista con las notificaciones de un cliente.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificaciones del cliente encontradas"),
            @ApiResponse(responseCode = "404", description = "Notificaciones no encontradas")
        })
        public ResponseEntity<List<Notificacion>> getNotificacionByCliente(@PathVariable @Parameter(description = "ID del cliente") String idCliente) {
            List<Notificacion> notificacionsDeCliente = notificacionService.findByCliente(idCliente);
            if (!notificacionsDeCliente.isEmpty()) {
                return new ResponseEntity<>(notificacionsDeCliente, HttpStatus.OK);
            } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        }
    
}
