package com.example.demo.repository;

import com.example.demo.model.DetalleCarrito;
import com.example.demo.model.Factura;
import com.example.demo.model.Favorito;

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

    public DetalleCarrito save(DetalleCarrito detalleCarrito) {
    	  entityManager.persist(detalleCarrito);
          return detalleCarrito;
    }

    public DetalleCarrito findById(int id) {
    	  Query query = entityManager.createNativeQuery("SELECT * FROM DetalleCarrito WHERE detcarrito_id = :id", DetalleCarrito.class);
          query.setParameter("id", id);
          try {
              return (DetalleCarrito) query.getSingleResult();
          } catch (Exception e) {
              return null;
          }
    }

    /*
    public void deleteByProductoIdAndUsuarioId(int productoId, Integer idUsuario) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getProducto() != null
                    && baseDeDatos.get(i).getProducto().getProd_id() == productoId
                    && baseDeDatos.get(i).getCarritoCompras() != null
                    && baseDeDatos.get(i).getCarritoCompras().getUsuario().getUsua_id().equals(idUsuario)) {
                baseDeDatos.remove(i);
                break;
            }
        }
    } 

    public DetalleCarrito findByCarritoIdAndIdProducto(int idCarrito, Integer idProducto) {
        for (DetalleCarrito detalleCarrito : baseDeDatos) {
            if (detalleCarrito.getCarritoCompras() != null
                    && detalleCarrito.getCarritoCompras().getCarritoId() == idCarrito
                    && detalleCarrito.getProducto().getProd_id().equals(idProducto)) {
                return detalleCarrito;
            }
        }
        return null;
    }

    public List<DetalleCarrito> findByIdUsuario(Integer idUsuario) {
        List<DetalleCarrito> detallesCarrito = new ArrayList<>();
        for (DetalleCarrito detalleCarrito : baseDeDatos) {
            if (detalleCarrito.getCarritoCompras() != null
                    && detalleCarrito.getCarritoCompras().getUsuario().getUsua_id().equals(idUsuario)) {
                detallesCarrito.add(detalleCarrito);

            }
        }
        return detallesCarrito;
    }*/


    public List<DetalleCarrito> findAll() {
    	Query query = entityManager.createNativeQuery("SELECT * FROM DetalleCarrito", DetalleCarrito.class);
        return query.getResultList();
    }

    public void deleteById(int id) {
    	Query query = entityManager.createNativeQuery("DELETE FROM DetalleCarrito WHERE detcarrito_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public DetalleCarrito update(DetalleCarrito detalleCarrito) {
    	 entityManager.merge(detalleCarrito);
         return detalleCarrito;
    }
/*
    public List<DetalleCarrito> buscarPorFiltros(int idDetalleCarrito, int cantidad) {
        List<DetalleCarrito> resultado = new ArrayList<>();
        for (DetalleCarrito detalleCarrito : baseDeDatos) {
            boolean coincide = true;

            if (idDetalleCarrito != 0 && detalleCarrito.getDetalleCarritoId() != idDetalleCarrito) {
                coincide = false;
            }

            if (cantidad != 0 && detalleCarrito.getDetalleCarritoId() != cantidad) {
                coincide = false;
            }

            if (coincide) {
                resultado.add(detalleCarrito);
            }
        }

        // Si no se encuentra ningún resultado, se devuelve una lista vacía
        if (resultado.isEmpty()) {
            return new ArrayList<>();
        }
        // Si se encuentra al menos un resultado, se devuelve la lista de resultados
        return resultado;
    }*/
}
