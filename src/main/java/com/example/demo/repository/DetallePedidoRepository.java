package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.model.DetallePedido;
import com.example.demo.model.Notificacion;
import com.example.demo.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class DetallePedidoRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	//guardar detalles
	public DetallePedido save(DetallePedido detalle) {
		entityManager.persist(detalle); 
		return detalle; 	
	}

	
	//obtener detalles por pedido
	public DetallePedido findByPedido(int idPedido) {
		Query query = entityManager.createNativeQuery("SELECT * FROM DetallePedido WHERE pedi_id = :idPedido", DetallePedido.class);
		query.setParameter("idPedido", idPedido); // Establece el parámetro de la consulta
		DetallePedido detalle = (DetallePedido) query.getSingleResult(); // Obtiene el resultado único
		return detalle; 
	}
	
	//actualizar detalles de pedido
	public DetallePedido update(DetallePedido detalle) {
		entityManager.merge(detalle); 
		return detalle; 
	}

	//eliminar detalles de pedido
	public void deletedById(Integer id) {
		Query query = entityManager.createNativeQuery("DELETE FROM DetallePedido WHERE pedi_id = :id");
		query.setParameter("id", id); // Establece el parámetro de la consulta
		query.executeUpdate(); // Ejecuta la consulta de eliminación
	}
	
}


