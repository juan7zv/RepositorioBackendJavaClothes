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

    private NotificacionRepository notificacionRepository; // Inyectar el repositorio de notificaciones
    private UsuarioRepository usuariorepository; //traer clientes de ejemplo para usar en initSampleData
    private PedidoRepository pedidoRepository; //traer pedidos de ejemplo para usar en initSampleData

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository, UsuarioRepository usuariorepository,
                                PedidoRepository pedidoRepository) {
        this.notificacionRepository = notificacionRepository; // Inicializar el repositorio
        this.pedidoRepository = pedidoRepository;
        this.usuariorepository = usuariorepository;
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
    public List<Notificacion> findByCliente(String clienteId) {
        return notificacionRepository.findByCliente(clienteId); // Llamar al método del repositorio
    }

    //Actualizar una notificacion
    public Notificacion update(Notificacion notificacion) {
        return notificacionRepository.update(notificacion); // Llamar al método del repositorio
    }

}
