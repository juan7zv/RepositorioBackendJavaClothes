package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
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
    @Transactional
    public DetallePedido save(DetallePedido detalle) {
        if (detalle.getDetalleId() == 0) {
            entityManager.persist(detalle);
        } else {
            detalle = entityManager.merge(detalle);
        }
        return detalle;
    }

    //obtener detalles por id de pedido
    public List<DetallePedido> findByPedido(int idPedido) {
        Query query = entityManager.createNativeQuery("SELECT * FROM detalle_pedido WHERE pedi_id = :idPedido", DetallePedido.class);
        query.setParameter("idPedido", idPedido); // Establece el parámetro de la consulta
        List<DetallePedido> detalle = query.getResultList();
        return detalle;
    }

    //actualizar detalles de pedido
    @Transactional
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

