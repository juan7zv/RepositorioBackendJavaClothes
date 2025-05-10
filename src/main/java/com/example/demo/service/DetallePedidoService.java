package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DetallePedido;
import com.example.demo.model.Pedido;
import com.example.demo.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService {
	private final DetallePedidoRepository detallePedidoRepository; // Inyectar el repositorio de detalles de pedido
	
	@Autowired //Autowired es una anotación de Spring que permite inyectar dependencias
	public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
		this.detallePedidoRepository = detallePedidoRepository; 
	}
	
	//guardar un detalle de pedido
	public DetallePedido save(DetallePedido detallePedido) {
		return detallePedidoRepository.save(detallePedido);	}
	
	//eliminar un detalle de pedido
	public void deleteById(Integer id) {
		detallePedidoRepository.deletedById(id); 
	}
	
	
	//actualizar un detalle de pedido
	public DetallePedido update(DetallePedido detallePedido) {
		return detallePedidoRepository.update(detallePedido);
	}
	
	//obtener detalles por pedido
	public DetallePedido findByPedido(Pedido pedido) {
		return detallePedidoRepository.findByPedido(pedido.getPedi_id());
	}
	
}
