package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class PedidoRepository {
	
	@PersistenceContext
	private EntityManager entityManager; // Inyecta el EntityManager para interactuar con la base de datos
	
	//Obtener todos los pedidos
    public List<Pedido> findAll() {
    	Query query = entityManager.createNativeQuery("SELECT * FROM Pedido", Pedido.class);
        return query.getResultList();
    }

    //Obtener un pedido por ID
    public Pedido findById(Integer id) {
    	Query query = entityManager.createNativeQuery("SELECT * FROM Pedido WHERE pedi_id = :id", Pedido.class);
    	query.setParameter("id", id);
		Pedido pedido = (Pedido) query.getSingleResult();
		return pedido;
	}

    //guardar un pedido
    public Pedido save(Pedido pedido) {
    	entityManager.persist(pedido);
		return pedido;
    }

    //eliminar un pedido (preguntar si se necesita)
    public boolean delete(Integer id) {
    Query query = entityManager.createNativeQuery("DELETE * FROM Pedido WHERE pedi_id = :id");
   query.setParameter("id", id); // Establece el parámetro de la consulta
   int rowsDeleted = query.executeUpdate(); 
   return rowsDeleted > 0; // Devuelve true si se eliminó al menos un pedido
    }

    //Actualizar un pedido
    public Pedido update(Pedido pedido) {
       	entityManager.merge(pedido);
       	return pedido;
    }

    //filtrar por criterios
   /* public List<Pedido> buscarPedidos(String clienteId, EstadosPedido estado, LocalDate fecha) {

    }*/
 }
