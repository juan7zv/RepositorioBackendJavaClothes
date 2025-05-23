package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // crear un nuevo producto
    @Transactional
    public Producto save(Producto producto) {
        entityManager.persist(producto);
        return producto;
    }

    // consultar un producto por su id
    public Producto findById(Integer id) {
        Query query = entityManager.createNativeQuery(
            "SELECT * FROM producto WHERE prod_id = :id", Producto.class);
                query.setParameter("id", id);
                try {
                    return (Producto) query.getSingleResult();
                } catch (Exception e) {
                    return null;
                }
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM producto", Producto.class);
        return query.getResultList();
    }

    // Actualizar un producto por su id
    @Transactional
    public Producto update(Producto producto) {
       entityManager.merge(producto);
         return producto;
    }

    // Eliminar un producto por su id
    @Transactional
    public void deleteById(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM producto WHERE prod_id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
    }

    // Obtener el Stock de un producto por su id
    public int obtenerStockProducto(int idProducto) {
        Query query = entityManager.createNativeQuery("SELECT stock FROM producto WHERE prod_id = :idProducto");
        query.setParameter("idProducto", idProducto);
        try {
            return (int) query.getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }



}