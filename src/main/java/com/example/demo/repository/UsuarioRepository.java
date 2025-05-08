package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // CREATE
    @Transactional
    public Usuario save(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    // READ BY ID
    public Usuario findById(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM usuario WHERE usua_id = :id", Usuario.class);
                query.setParameter("id", id);
                try {
                    return (Usuario) query.getSingleResult();
                } catch (Exception e) {
                    return null;
                }
    }

    // READ ALL
    public List<Usuario> findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM usuario", Usuario.class);
        return query.getResultList();
    }

    // UPDATE
    @Transactional
    public Usuario update(Usuario usuario) {
        entityManager.merge(usuario);
        return usuario;
    }

    // DELETE
    @Transactional
    public void deleteById(Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM usuario WHERE usua_id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
    }


    /*
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
                    if (usuario.getIdUsuario().equals(token)) {
                        return usuario;
                    }
                }
            }
        }
        return null;
    } */
}