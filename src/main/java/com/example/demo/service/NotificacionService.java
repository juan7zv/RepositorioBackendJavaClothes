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
        initSampleData(); // Inicializar datos de ejemplo
    }

    // Método para inicializar datos de ejemplo
    public void initSampleData() {
    	Usuario user1 = usuariorepository.findById("123456");
    	Usuario user2 = usuariorepository.findById("234567");
    	Usuario user3 = usuariorepository.findById("345678");
    	Notificacion notificacion1 = new Notificacion("1", "Su pedido está listo para ser reclamado.", "4/04/2025",user1 , null);
    	
		notificacionRepository.save(notificacion1);
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
    public Notificacion findById(String id) {
        return notificacionRepository.findById(id);
    }

    //eliminar una notificacion
    public void deleteById(String id) {
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
