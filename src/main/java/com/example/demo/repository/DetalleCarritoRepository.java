package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.DetalleCarrito;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleCarritoRepository {
    private final List<DetalleCarrito> baseDeDatos = new ArrayList<>();

    public DetalleCarrito save(DetalleCarrito detalleCarrito) {
        baseDeDatos.add(detalleCarrito);
        return detalleCarrito;
    }

    public DetalleCarrito findById(int id) {
        for (DetalleCarrito detalleCarrito : baseDeDatos) {
            if (detalleCarrito.getDetalleCarritoId() == id) {
                return detalleCarrito;
            }
        }
        return null;
    }

    public DetalleCarrito findByCarritoIdAndIdProducto(int idCarrito, Integer idProducto) {
        for (DetalleCarrito detalleCarrito : baseDeDatos) {
            if (detalleCarrito.getCarritoCompras() !=  null
                    && detalleCarrito.getCarritoCompras().getCarritoId() == idCarrito
                    && detalleCarrito.getProducto().getProductoId().equals(idProducto)) {
                return detalleCarrito;
            }
        }
        return null;
    }

    public List<DetalleCarrito> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteById(int id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getDetalleCarritoId() == id) {
                baseDeDatos.remove(i);
                break;
            }
        }
    }

    public DetalleCarrito update(DetalleCarrito detalleCarrito) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getDetalleCarritoId() == detalleCarrito.getDetalleCarritoId()) {
                baseDeDatos.set(i, detalleCarrito);
                return detalleCarrito;
            }
        }
        return null;
    }

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
    }
}
