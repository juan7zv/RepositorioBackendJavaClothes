package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final List<Usuario> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    public Usuario save(Usuario usuario) {
        baseDeDatos.add(usuario);
        authTokens.add(usuario.getId());
        return usuario;
    }

    public Usuario findById(String id) {
        for (Usuario usuario : baseDeDatos) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> findAll() {
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

    public Usuario update(Usuario usuario) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(usuario.getId())) {
                baseDeDatos.set(i, usuario);
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorFiltros(String nombre, String email) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : baseDeDatos) {
            boolean coincideNombre = (nombre == null || usuario.getNombre().contains(nombre));
            boolean coincideEmail = (email == null || usuario.getEmail().contains(email));
            if (coincideNombre && coincideEmail) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    public Usuario findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
            	for (Usuario usuario : baseDeDatos) {
                    if (usuario.getId().equals(token)) {
                        return usuario;
                    }
                }
            }
        }
        return null;
    }
}