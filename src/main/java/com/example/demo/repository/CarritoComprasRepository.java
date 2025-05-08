
package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CarritoCompras;
import org.springframework.stereotype.Repository;
@Repository
public class CarritoComprasRepository {
     private final List<CarritoCompras> baseDeDatos = new ArrayList<>();

    public CarritoCompras save(CarritoCompras carritoCompras) {
        baseDeDatos.add(carritoCompras);
        return carritoCompras;
    }

    public CarritoCompras findById(Integer id) {
        for ( CarritoCompras carritoCompras : baseDeDatos) {
            if (carritoCompras.getCarritoId().equals(id)) {
                return carritoCompras;
            }
        }
        return null;
    }

    public CarritoCompras findByIdAndUsuario(Integer id, Integer usuarioId) {
        for ( CarritoCompras carritoCompras : baseDeDatos) {
            if (carritoCompras.getCarritoId().equals(id)
                    && carritoCompras.getUsuario().getUsua_id().equals(usuarioId)) {
                return carritoCompras;
            }
        }
        return null;
    }

    public List<CarritoCompras> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteById(Integer id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getCarritoId().equals(id)) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }

    public CarritoCompras update(CarritoCompras CarritoCompras) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getCarritoId().equals(CarritoCompras.getCarritoId())) {
                baseDeDatos.set(i, CarritoCompras);
                return CarritoCompras;
            }
        }
        return null;
    }

    public List<CarritoCompras> buscarPorFiltros(Integer carritoId, String usuario) {
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
    }
    
}
