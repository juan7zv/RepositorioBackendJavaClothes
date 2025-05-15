package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Usuario;
import com.example.demo.model.enums.RolUsuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder,UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

 
    // Métodos CRUD
    public Usuario save(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> update(Integer id,Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.update(id, usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    /*
    public List<Usuario> buscarPorFiltros(String nombre, String email) {
        return usuarioRepository.buscarPorFiltros(nombre, email);
    }

    public Usuario findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    } */

    /*
    // Método para autenticar un usuario
    public Optional<Usuario> login(Integer id, String clave)  {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario != null && usuario.getClave().equals(clave)) {
            // Lógica de inicio de sesión exitosa
            System.out.println("Inicio de sesión exitoso para el usuario: " + usuario.getNombre());
            return usuario;
        } else {
            // Lógica de inicio de sesión fallido
            System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
            return null;
        }
    } */
}