package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;
import com.example.demo.model.enums.RolUsuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        initSampleData();
    }

    private void initSampleData() {

        // Definimos los roles de usuario
        RolUsuario rolAdmin = RolUsuario.Administrador;
        RolUsuario rolCliente = RolUsuario.Cliente;
        RolUsuario rolVendedor = RolUsuario.Vendedor;

        // Creamos 8 instancias de Usuario
        Usuario usuario1 = new Usuario("123456","Ana Sofia Henao Pérez", "ana@example.com", "3012345678", rolAdmin, "clave123");
        Usuario usuario2 = new Usuario("234567","Carlos Gómez", "carlos@example.com", "3023456789", rolCliente, "pass456");
        Usuario usuario3 = new Usuario("345678","Laura Torres", "laura@example.com", "3034567890", rolVendedor, "segura789");
        Usuario usuario4 = new Usuario("456789","Diego Ramírez", "diego@example.com", "3045678901", rolAdmin, "adminPass");
        Usuario usuario5 = new Usuario("000000","Sofía Herrera", "sofia@example.com", "3056789012", rolCliente, "sofia2024");
        Usuario usuario6 = new Usuario("999999","Fernando Díaz Hurtado", "fernando@example.com", "3067890123", rolVendedor, "fernandoKey");
        Usuario usuario7 = new Usuario("888888","Isabel Rojas", "isabel@example.com", "3078901234", rolAdmin, "rojasPass");
        Usuario usuario8 = new Usuario("777777","Ricardo de Jesus Mendoza Smith", "ricardo@example.com", "3089012345", rolCliente, "ricardoSeg");

        save(usuario1);
        save(usuario2);
        save(usuario3);
        save(usuario4);
        save(usuario5);
        save(usuario6);
        save(usuario7);
        save(usuario8);

    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorFiltros(String nombre, String email) {
        return usuarioRepository.buscarPorFiltros(nombre, email);
    }

    public Usuario findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    }

    public Usuario login(String id, String clave)  {
        Usuario usuario = usuarioRepository.findById(id);

        if (usuario != null && usuario.getClave().equals(clave)) {
            // Lógica de inicio de sesión exitosa
            System.out.println("Inicio de sesión exitoso para el usuario: " + usuario.getNombre());
            return usuario;
        } else {
            // Lógica de inicio de sesión fallido
            System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
            return null;
        }
    }
}