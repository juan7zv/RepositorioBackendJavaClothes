package com.example.demo.repository;


import com.example.demo.model.Factura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacturaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // CREATE
    @Transactional
    public Factura save(Factura factura) {
        entityManager.persist(factura);
        return factura;
    }

    // READ BY ID
    public Factura findById(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM factura WHERE fac_id = :id", Factura.class);
        query.setParameter("id", id);
        try {
            return (Factura) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // READ ALL
    public List<Factura> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM factura", Factura.class);
        return query.getResultList();
    }

    // UPDATE
    @Transactional
    public Factura update(Factura factura) {
        entityManager.merge(factura);
        return factura;
    }

    // DELETE
    @Transactional
    public void deleteById(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM factura WHERE fac_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
