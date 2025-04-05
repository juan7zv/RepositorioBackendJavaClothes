package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DetalleCarrito;
import com.example.demo.repository.DetalleCarritoRepository;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DetalleCarritoService {
    private final DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    public DetalleCarritoService(DetalleCarritoRepository detalleCarritoRepository) {
		this.detalleCarritoRepository = detalleCarritoRepository;
		initSampleData();
	}

    private void initSampleData() {
        DetalleCarrito detalleCarrito1 = new DetalleCarrito(1, 2, null, null);
        DetalleCarrito detalleCarrito2 = new DetalleCarrito(2, 3, null, null);
        DetalleCarrito detalleCarrito3 = new DetalleCarrito(3, 1, null, null);
        save(detalleCarrito1);
        save(detalleCarrito2);
        save(detalleCarrito3);
        
    }

    public DetalleCarrito save(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public DetalleCarrito findById(int id) {
        return detalleCarritoRepository.findById(id);
    }

    public List<DetalleCarrito> findAll() {
        return detalleCarritoRepository.findAll();
    }

    public DetalleCarrito update(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.update(detalleCarrito);
    }

    public void deleteById(int id) {
    	detalleCarritoRepository.deleteById(id);
    }

    public List<DetalleCarrito> buscarPorFiltros(int idCarrito, int idProducto) {
        return detalleCarritoRepository.buscarPorFiltros(idCarrito, idProducto);
    }
}
