package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notificacion;
import com.example.demo.repository.NotificacionRepository;

@Service
public class NotificacionService {
	private NotificacionRepository notificacionRepository; // Inyectar el repositorio de notificaciones
	
	@Autowired
	public NotificacionService(NotificacionRepository notificacionRepository) {
		this.notificacionRepository = notificacionRepository; // Inicializar el repositorio
		initSampleData(); // Inicializar datos de ejemplo
	}
	
	// Método para inicializar datos de ejemplo
	public void initSampleData() {
		
	}
	
	//guardar una notificacion
	public Notificacion save(Notificacion notificacion) {
		return notificacionRepository.save(notificacion); // Llamar al método save del repositorio
	}
	
	//obtener todas las notificaciones
	public List<Notificacion> findAll() {
		return notificacionRepository.findAll(); 
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
