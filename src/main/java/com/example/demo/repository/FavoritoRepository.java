package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Factura;
import com.example.demo.model.Favorito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class FavoritoRepository {
	@PersistenceContext
    private EntityManager entityManager;

	@Transactional
    public Favorito save(Favorito favorito) {
		entityManager.persist(favorito);
        return favorito;
    }

    // metodo para buscar un favorito por id de producto y id de usuario
    public Favorito findByProductoAndUsuario(int idProducto, int idUsuario) {
        Query query = entityManager.createNativeQuery("SELECT * FROM favorito WHERE prod_id = :idProducto AND usua_id = :idUsuario", Favorito.class);
        query.setParameter("idProducto", idProducto);
        query.setParameter("idUsuario", idUsuario);
        try {
            return (Favorito) query.getSingleResult(); // Retorna un único resultado
        } catch (Exception e) {
            return null; // Retorna null si no se encuentra el favorito
        }
    }

    public Favorito findById(int idFav) {
        Query query = entityManager.createNativeQuery("SELECT * FROM favorito WHERE favorito_id = :idFav", Favorito.class);
        query.setParameter("idFav", idFav);
        try {
            return (Favorito) query.getSingleResult(); //
        } catch (Exception e) {
            return null;
        }
    }

    public List<Favorito> findByCliente(Integer idUser) {
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM favorito WHERE usua_id = :idUser", Favorito.class);
            query.setParameter("idUser", idUser);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>(); // Retorna una lista vacía si no se encuentra el cliente
        }
    }

    public List<Favorito> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM favorito", Favorito.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(Integer id) {
    	 Query query = entityManager.createNativeQuery("DELETE FROM favorito WHERE favo_id = :id");
         query.setParameter("id", id);
         query.executeUpdate();
    }
    @Transactional
    public Favorito update(Favorito favorito) {
    	  entityManager.merge(favorito);
          return favorito;
    }

}
