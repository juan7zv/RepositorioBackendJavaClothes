package com.example.demo.repository;

import java.util.ArrayList;
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

    // CREATE
    @Transactional
    public Producto save(Producto producto) {
        entityManager.persist(producto);
        return producto;
    }

    // READ BY ID
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

    // READ ALL
    public List<Producto> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM producto", Producto.class);
        return query.getResultList();
    }

    // UPDATE
    @Transactional
    public Producto update(Producto producto) {
       entityManager.merge(producto);
         return producto;
    }

    // DELETE
    @Transactional
    public void deleteById(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM producto WHERE prod_id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
    }


 /*   TODO  // Método para buscar productos por filtros
    public List<Producto> buscarPorFiltros(String nombre, Double precioMin, Double precioMax) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : baseDeDatos) {
            boolean coincideNombre = (nombre == null || producto.getNombre().contains(nombre));
            boolean coincidePrecioMin = (precioMin == null || producto.getPrecio() >= precioMin);
            boolean coincidePrecioMax = (precioMax == null || producto.getPrecio() <= precioMax);
            if (coincideNombre && coincidePrecioMin && coincidePrecioMax) {
                resultado.add(producto);
            }
        }
        return resultado;
    } */

}