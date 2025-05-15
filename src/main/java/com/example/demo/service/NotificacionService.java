package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notificacion;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.NotificacionRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class NotificacionService {

    private NotificacionRepository notificacionRepository;
    
    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
    	this.notificacionRepository = notificacionRepository;
    }


    //guardar una notificacion
    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion); // Llamar al método save del repositorio
    }

    //obtener todas las notificaciones
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    //obtener notificacion por id
    public Notificacion findById(Integer id) {
        return notificacionRepository.findById(id);
    }

    //eliminar una notificacion
    public void deleteById(Integer id) {
        notificacionRepository.deletedById(id);
    }

    //Buscar notificaciones por cliente
    public List<Notificacion> findByCliente(int clienteId) {
        return notificacionRepository.findByCliente(clienteId); // Llamar al método del repositorio
    }

    //Actualizar una notificacion
    public Notificacion update(Notificacion notificacion) {
        return notificacionRepository.update(notificacion); // Llamar al método del repositorio
    }

}
