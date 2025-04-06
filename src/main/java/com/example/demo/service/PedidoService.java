package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pedido;
import com.example.demo.model.enums.EstadosPedido;
import com.example.demo.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository; // Inyectar el repositorio de pedidos

    @Autowired //Autowired es una anotación de Spring que permite inyectar dependencias
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository; // Inicializar el repositorio
        initSampleData(); // Inicializar datos de ejemplo
    }

    // Método para inicializar datos de ejemplo
    private void initSampleData() {
		//Pedido pedido1 = new Pedido("hhh", "hcshcsh", "Disponible", LocalDate fecha, ArrayList<DetallePedido> detallesVenta);
		
		//save(pedido1);
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
    public Pedido findById(String id) {
        return pedidoRepository.findById(id);
    }

    //eliminar un pedido
    public void deleteById(String id) {
        pedidoRepository.deletedByIdPedido(id);
    }

    //actualizar un pedido
    public Pedido update(Pedido pedido) {
        return pedidoRepository.update(pedido);
    }

    //buscar pedidos por filtros
    public List<Pedido> buscarPedidos(String clienteId, EstadosPedido estado, LocalDate fecha) {
        return pedidoRepository.buscarPedidos(clienteId, estado, fecha);
    }
}
