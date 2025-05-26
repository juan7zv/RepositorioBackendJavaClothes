package com.example.demo.service;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository; // Inyectar el repositorio de pedidos
    private final UsuarioService usuarioService;

    @Autowired //Autowired es una anotación de Spring que permite inyectar dependencias
    public PedidoService(PedidoRepository pedidoRepository, UsuarioService usuarioService) {
        this.pedidoRepository = pedidoRepository; // Inicializar el repositorio
        this.usuarioService = usuarioService;

    }

    // encontrar un pedido por id de usuario
    public Optional<Pedido> findByUsuarioId(Integer usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId); // Llamar al método del repositorio
    }


    //guardar un pedido
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido); // Llamar al método save del repositorio
    }

    //obtener todos los pedidos
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    //obtener un pedido por ID
    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    //eliminar un pedido
    public void deleteById(Integer id) {
        pedidoRepository.delete(id);
    }

    //actualizar un pedido
    public Pedido update(Pedido pedido) {
        return pedidoRepository.update(pedido);
    }

}
