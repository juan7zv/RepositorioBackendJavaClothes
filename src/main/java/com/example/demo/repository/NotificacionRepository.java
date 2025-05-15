package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Notificacion;
import com.example.demo.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class NotificacionRepository {


	@PersistenceContext
	private EntityManager entityManager; // Inyecta el EntityManager para interactuar con la base de datos
	

	 // Obtener todas las notificaciones
    public List<Notificacion> findAll() {
    	Query query = entityManager.createNativeQuery("SELECT * FROM notificacion", Notificacion.class);
        return query.getResultList();
    }
    
    //Buscar notificación por ID
    public Notificacion findById(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM notificacion WHERE noti_id = :id", Notificacion.class);
        query.setParameter("id", id);
        Notificacion notificacion = (Notificacion) query.getSingleResult();
        return notificacion;
    }
    
    // Guardar notificación
    public Notificacion save(Notificacion notificacion) {
        entityManager.persist(notificacion);
        return notificacion;
    }



    //Buscar notificaciones por cliente
    public List<Notificacion> findByCliente(Integer id) {
    	Query query = entityManager.createNativeQuery("SELECT * FROM notificacion WHERE usua_id = :id", Notificacion.class);
    	query.setParameter("id", id);
    	Notificacion notificacion = (Notificacion) query.getSingleResult(); 
    	return query.getResultList();
    }

    //Eliminar notificación por ID
    public boolean deletedById(Integer id) {
    	Query query = entityManager.createNativeQuery("DELETE FROM notificacion WHERE noti_id = :id");
    	query.setParameter("id", id); // Establece el parámetro de la consulta
    	int rowsDeleted = query.executeUpdate(); 
    	return rowsDeleted > 0; 
    }

    //Actualizar notificación
    public Notificacion update(Notificacion notificacion) {
    	entityManager.merge(notificacion);
    	return notificacion;
    }


}
