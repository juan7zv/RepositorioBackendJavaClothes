package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.DetalleCarrito;
import com.example.demo.repository.DetalleCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DetalleCarritoService {
    private final DetalleCarritoRepository detalleCarritoRepository;
    private final ProductoService productoService;

    @Autowired
    public DetalleCarritoService(DetalleCarritoRepository detalleCarritoRepository, ProductoService productoService) {
		this.detalleCarritoRepository = detalleCarritoRepository;
        this.productoService = productoService;
    }


    /*public DetalleCarrito saveProductoCompra(Integer idProducto, CarritoCompras carritoCompras) {
        DetalleCarrito detalleCarrito = detalleCarritoRepository.findByCarritoIdAndIdProducto(carritoCompras.getCarritoId(), idProducto);
        if(detalleCarrito != null) {
            detalleCarrito.setCantidad(detalleCarrito.getCantidad());
            return detalleCarritoRepository.update(detalleCarrito);
        } else {
            detalleCarrito = new DetalleCarrito(1, 1, carritoCompras, productoService.findById(idProducto));
            return detalleCarritoRepository.save(detalleCarrito);
        }
    }*/

    public DetalleCarrito save(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

   public DetalleCarrito findById(int id) {
        return detalleCarritoRepository.findById(id);
    }
   /*
    public void deleteByProductoIdAndUsuarioId(int productoId, Integer idUsuario) {
        detalleCarritoRepository.deleteByProductoIdAndUsuarioId(productoId, idUsuario);
    }*/

    public List<DetalleCarrito> findAll() {
        return detalleCarritoRepository.findAll();
    }
    /*
    public List<DetalleCarrito> findByIdUsuario(Integer idUsuario) {
        return detalleCarritoRepository.findByIdUsuario(idUsuario);
    }*/

    public DetalleCarrito update(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.update(detalleCarrito);
    }

    public void deleteById(int id) {
    	detalleCarritoRepository.deleteById(id);
    }
    
/*
    public List<DetalleCarrito> buscarPorFiltros(int idCarrito, int idProducto) {
        return detalleCarritoRepository.buscarPorFiltros(idCarrito, idProducto);
    }*/
}
