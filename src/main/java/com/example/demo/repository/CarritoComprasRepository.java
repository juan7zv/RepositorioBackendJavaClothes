
package com.example.demo.repository;

import com.example.demo.model.CarritoCompras;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarritoComprasRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CarritoCompras save(CarritoCompras carritoCompras) {
        entityManager.persist(carritoCompras);
        return carritoCompras;
    }

    // Buscar todos los carritos de compras
    public List<CarritoCompras> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM carrito_compras", CarritoCompras.class);
        return query.getResultList();
    }

    // Buscar carrito de compras por ID de carrito
    public CarritoCompras findById(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM carrito_compras " +
                "WHERE carrito_id = :id", CarritoCompras.class);
        query.setParameter("id", id);
        try {
            return (CarritoCompras) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

   // Buscar carrito de compras por ID de usuario
    public Optional<CarritoCompras> findByUsuarioId(Integer usuarioId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM carrito_compras " +
                "WHERE usua_id = :usuarioId", CarritoCompras.class);
        query.setParameter("usuarioId", usuarioId);

        CarritoCompras result = null;
        try {
            result = (CarritoCompras) query.getSingleResult();
        } catch (Exception e) {
            // Si no se encuentra resultado, se maneja silenciosamente
        }
        return Optional.ofNullable(result);
    }

    // Crear un nuevo carrito de compras, cada carrito debe ir asociado a un cliente.
    public CarritoCompras findByIdAndUsuario(Integer id, Integer usuarioId) {
        try {
            Query query = entityManager.createNativeQuery(
                    "SELECT * FROM carrito_compras WHERE carrito_id = :id AND usua_id = :usuarioId",
                    CarritoCompras.class
            );
            query.setParameter("id", id);
            query.setParameter("usuarioId", usuarioId);

            return (CarritoCompras) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void deleteById(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM carrito_id WHERE favo_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public CarritoCompras update(CarritoCompras carritoCompras) {
        entityManager.merge(carritoCompras);
        return carritoCompras;
    }

   /* public List<CarritoCompras> buscarPorFiltros(Integer carritoId, String usuario) {
        List<CarritoCompras> resultado = new ArrayList<>();
        for (CarritoCompras carritoCompras : baseDeDatos) {
        				boolean coincide = true;
			if (carritoId != null && !carritoCompras.getCarritoId().equals(carritoId)) {
				coincide = false;
			}
			if (usuario != null && !carritoCompras.getUsuario().getNombre().equals(usuario)) {
				coincide = false;
			}
			if (coincide) {
				resultado.add(carritoCompras);
			}
        }
        return resultado;
    }*/

}
