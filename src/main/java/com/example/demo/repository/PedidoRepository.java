package com.example.demo.repository;

import com.example.demo.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository {

    @PersistenceContext
    private EntityManager entityManager; // Inyecta el EntityManager para interactuar con la base de datos

    // buscar pedido por id de usuario
    public Optional<Pedido> findByUsuarioId(Integer id) {
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM Pedido WHERE usua_id = :id", Pedido.class);
            query.setParameter("id", id);
            Pedido pedido = (Pedido) query.getSingleResult();
            return Optional.of(pedido);
        } catch (Exception e) {
            return Optional.empty(); // Retorna un Optional vacío si no se encuentra el pedido
        }
    }

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
    @Transactional
    public Pedido save(Pedido pedido) {
        entityManager.persist(pedido);
        return pedido;
    }

    //eliminar un pedido (preguntar si se necesita)
    @Transactional
    public boolean delete(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE * FROM Pedido WHERE pedi_id = :id");
        query.setParameter("id", id); // Establece el parámetro de la consulta
        int rowsDeleted = query.executeUpdate();
        return rowsDeleted > 0; // Devuelve true si se eliminó al menos un pedido
    }

    //Actualizar un pedido
    @Transactional
    public Pedido update(Pedido pedido) {
        entityManager.merge(pedido);
        return pedido;
    }


}
