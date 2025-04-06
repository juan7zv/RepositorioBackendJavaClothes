package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Favorito;

import org.springframework.stereotype.Repository;

@Repository
public class FavoritoRepository {
    private final List<Favorito> baseDeDatos = new ArrayList<>();

    public Favorito save(Favorito favorito) {
        baseDeDatos.add(favorito);
        return favorito;
    }

    public Favorito findById(int id) {
        for (Favorito favorito : baseDeDatos) {
            if (favorito.getFavoritoId() == id) {
                return favorito;
            }
        }
        return null;
    }

    public List<Favorito> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteById(int id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getFavoritoId() == id) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }

    public Favorito update(Favorito favorito) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
           if (baseDeDatos.get(i).getFavoritoId() == favorito.getFavoritoId()) {
				baseDeDatos.set(i, favorito);
				return favorito;
            }
        }
        return null;
    }

    public List<Favorito> buscarPorFiltros(int idFavorito, String usuario, String producto) {
        List<Favorito> resultado = new ArrayList<>();
        for (Favorito favorito : baseDeDatos) {
            boolean coincideidFavorito = (idFavorito == 0 || favorito.getFavoritoId() == idFavorito);
            boolean coincideUsuario = (usuario == null || favorito.getUsuario().getNombre().contains(usuario));
            boolean coincideProducto = (producto == null || favorito.getProducto().getNombre().contains(producto));
            if (coincideidFavorito && coincideUsuario && coincideProducto) {
                resultado.add(favorito);
            }
        }
        return resultado;
    }
}
