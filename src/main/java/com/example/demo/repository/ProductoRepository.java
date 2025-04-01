package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Producto;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {
    private final List<Producto> baseDeDatos = new ArrayList<>();

    public Producto save(Producto producto) {
        baseDeDatos.add(producto);
        return producto;
    }

    public Producto findById(String id) {
        for (Producto producto : baseDeDatos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteById(String id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(id)) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }

    public Producto update(Producto producto) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(producto.getId())) {
                baseDeDatos.set(i, producto);
                return producto;
            }
        }
        return null;
    }

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
    }
}