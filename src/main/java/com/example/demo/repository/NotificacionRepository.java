package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Notificacion;

@Repository
public class NotificacionRepository {

    private List<Notificacion> notificaciones = new ArrayList<>();

    // Guardar notificación
    public Notificacion save(Notificacion notificacion) {
        notificaciones.add(notificacion);
        return notificacion;
    }

    //Buscar notificación por ID
    public Notificacion findById(String idNoti) {
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.getIdNotificacion().equals(idNoti)) {
                return notificacion;
            }
        }
        return null;
    }

    //Buscar notificaciones por cliente
    public List<Notificacion> findByCliente(String clienteId) {
        List<Notificacion> resultado = new ArrayList<>();
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.getCliente().getIdUsuario().equals(clienteId)) {
                resultado.add(notificacion);
            }
        }
        return resultado; // Devuelve una lista con las notificaciones del cliente
    }

    //Eliminar notificación por ID
    public void deletedById(String idNoti) {
        for (int i = 0; i < notificaciones.size(); i++) {
            if (notificaciones.get(i).getIdNotificacion().equals(idNoti)) {
                notificaciones.remove(i);
                return;
            }
        }
    }

    //Actualizar notificación
    public Notificacion update(Notificacion notificacion) {
        for (int i = 0; i < notificaciones.size(); i++) {
            if (notificaciones.get(i).getIdNotificacion().equals(notificacion.getIdNotificacion())) {
                notificaciones.set(i, notificacion); // Reemplaza la notificación existente con la nueva
                return notificacion;
            }
        }
        return null;
    }

    // Obtener todas las notificaciones
    public List<Notificacion> findAll() {
        return new ArrayList<>(notificaciones); // Devuelve una copia de la lista
    }
}
