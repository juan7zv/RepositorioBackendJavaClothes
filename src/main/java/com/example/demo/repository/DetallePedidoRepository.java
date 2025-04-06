package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.model.DetallePedido;
import com.example.demo.model.Pedido;

@Repository
public class DetallePedidoRepository {
	private final List<DetallePedido> detalles = new ArrayList<>();
	
	//guardar detalles
	public DetallePedido save(DetallePedido detalle) {
		detalles.add(detalle);
		return detalle;
	}

	
	//obtener detalles por pedido
	public List<DetallePedido> findByPedido(Pedido pedido) {
		List<DetallePedido> resultado = new ArrayList<>();
		for (DetallePedido detalle : detalles) {
			if (detalle.getPedido().equals(pedido)) {
				resultado.add(detalle);
			}
		}
		return resultado; // Devuelve una lista con los detalles del pedido
	}
	
	//actualizar detalles de pedido
	public DetallePedido update(DetallePedido detalle) {
		for (int i = 0; i < detalles.size(); i++) {
			if (detalles.get(i).getDetalleId().equals(detalle.getDetalleId())) {
				detalles.set(i, detalle); // Reemplaza el detalle existente con el nuevo
				return detalle;
			}
		}
		return null;
	}

	//eliminar detalles de pedido
	public void deletedById(String id) {
		detalles.removeIf(detalle -> detalle.getDetalleId().equals(id)); // Elimina el detalle con el ID especificado
	}
	
}


