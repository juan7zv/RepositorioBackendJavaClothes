package com.example.demo.repository;

import com.example.demo.model.DetalleCarrito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleCarritoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DetalleCarrito save(DetalleCarrito detalleCarrito) {
        // Verificar si ya existe el producto en el carrito
        DetalleCarrito existente = findByCarritoIdAndIdProducto(
                detalleCarrito.getCarritoCompras().getCarritoId(),
                detalleCarrito.getProducto().getProd_id()
        );

        if (existente != null) {
            throw new RuntimeException("El producto ya existe en el carrito");
        }

        entityManager.persist(detalleCarrito);
        return detalleCarrito;
    }


    public DetalleCarrito findById(int id) {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM detalle_carrito WHERE detalle_carrito_id = :id",
                DetalleCarrito.class);
        query.setParameter("id", id);
        try {
            return (DetalleCarrito) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public DetalleCarrito findByCarritoIdAndIdProducto(int idCarrito, int idProducto) {
        try {
            String jpql = "SELECT dc FROM DetalleCarrito dc  WHERE dc.carritoCompras.carritoId = :idCarrito AND dc.producto.prod_id = :idProducto";
            return entityManager.createQuery(jpql, DetalleCarrito.class)
                    .setParameter("idCarrito", idCarrito)
                    .setParameter("idProducto", idProducto)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<DetalleCarrito> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM detalle_carrito", DetalleCarrito.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = entityManager.createNativeQuery("DELETE FROM detalle_carrito WHERE detalle_carrito_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public DetalleCarrito update(DetalleCarrito detalleCarrito) {
        DetalleCarrito existente = findByCarritoIdAndIdProducto(
                detalleCarrito.getCarritoCompras().getCarritoId(),
                detalleCarrito.getProducto().getProd_id()
        );
        if (existente != null) {
            existente.setCantidad(detalleCarrito.getCantidad());
            entityManager.merge(existente);
            return existente;
        } else {
            throw new RuntimeException("El producto no existe en el carrito");
        }
    }

    public List<DetalleCarrito> findByCarritoId(int carritoId) {
        String jpql = "SELECT dc FROM DetalleCarrito dc WHERE dc.carritoCompras.carritoId = :carritoId";
        return entityManager.createQuery(jpql, DetalleCarrito.class)
                .setParameter("carritoId", carritoId)
                .getResultList();
    }


}
