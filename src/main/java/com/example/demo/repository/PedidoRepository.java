package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Pedido;
import com.example.demo.model.enums.EstadosPedido;

@Repository
public class PedidoRepository {

    private final List<Pedido> pedidos = new ArrayList<>();

    //Obtener todos los pedidos
    public List<Pedido> findAll() {
        return new ArrayList<>(pedidos); // Devuelve una copia de la lista
    }

    //Obtener un pedido por ID
    public Pedido findById(Integer id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getPedi_id().equals(id)) {
                return pedido;
            }
        }
        return null; // Si no se encuentra el pedido
    }

    //guardar un pedido
    public Pedido save(Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    //eliminar un pedido (preguntar si se necesita)
    public void deletedByIdPedido(Integer id) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getPedi_id().equals(id)) {
                pedidos.remove(i);
                return;
            }
        }
    }

    //Actualizar un pedido
    public Pedido update(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getPedi_id().equals(pedido.getPedi_id())) {
                pedidos.set(i, pedido);
                return pedido;
            }
        }
        return null; // Si no se encuentra el pedido
    }

    //filtrar por criterios
    public List<Pedido> buscarPedidos(String clienteId, EstadosPedido estado, LocalDate fecha) {
        List<Pedido> resultado = new ArrayList<>(); // Lista para almacenar los pedidos filtrados
        for (Pedido pedido : pedidos) {
            boolean coincideCliente = (clienteId == null || pedido.getCliente().getUsua_id().equals(clienteId));
            boolean coincideEstado = (estado == null || pedido.getEstado().equals(estado));
            boolean coincideFecha = (fecha == null || pedido.getFecha().toString().equals(fecha));
            if (coincideCliente && coincideEstado && coincideFecha) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

}
