
package com.example.demo.repository;
import com.example.demo.model.CarritoCompras;
import com.example.demo.model.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CarritoComprasRepository {
   @PersistenceContext
   private EntityManager entityManager;
   @Transactional
    public CarritoCompras save(CarritoCompras carritoCompras) {
	   entityManager.persist(carritoCompras);
		return carritoCompras;
      
    }

    public CarritoCompras findById(Integer id) {
    	   Query query = entityManager.createNativeQuery("SELECT * FROM CarritoCompras WHERE carrito_id = :id", CarritoCompras.class);
           query.setParameter("id", id);
           try {
               return (CarritoCompras) query.getSingleResult();
           } catch (Exception e) {
               return null;
           }
    }

    public CarritoCompras findByIdAndUsuario(Integer id, Integer usuarioId) {
        try {
            Query query = entityManager.createNativeQuery(
                "SELECT * FROM carritoCompras WHERE carrito_id = :id AND usua_id = :usuarioId", 
                CarritoCompras.class
            );
            query.setParameter("id", id);
            query.setParameter("usuarioId", usuarioId);

            return (CarritoCompras) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<CarritoCompras> findAll() {
    	  Query query = entityManager.createNativeQuery("SELECT * FROM CarritoCompras", CarritoCompras.class);
          return query.getResultList();
    }
    @Transactional
    public void deleteById(Integer id) {
    	Query query = entityManager.createNativeQuery("DELETE FROM favorito WHERE favo_id = :id");
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
