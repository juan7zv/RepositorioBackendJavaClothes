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

    // Obtener el stock del producto
    public int obtenerStockProducto(int idProducto) {
        String MENSAJE_PRODUCTO_NO_ENCONTRADO = "Producto no encontrado con ID: %d";
        String MENSAJE_ERROR_STOCK = "Error al obtener el stock del producto con ID: %d";
        try {
            var producto = productoService.findById(idProducto);
            if (producto == null) {
                throw new IllegalArgumentException(String.format(MENSAJE_PRODUCTO_NO_ENCONTRADO, idProducto));
            }
            return producto.getStock();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(String.format(MENSAJE_ERROR_STOCK, idProducto), e);
        }
    }


    // Validar si el producto ya existe en el carrito
    public boolean existsByCarritoIdAndProductoId(int carritoId, int productoId) {
        return detalleCarritoRepository.findByCarritoIdAndIdProducto(carritoId, productoId) != null;
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

    public List<DetalleCarrito> findByCarritoId(int carritoId) {
        return detalleCarritoRepository.findByCarritoId(carritoId);
    }

}