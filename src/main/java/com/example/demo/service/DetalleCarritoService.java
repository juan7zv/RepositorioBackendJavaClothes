package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DetalleCarritoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        initSampleData();
    }

    private void initSampleData() {
        Producto laptop = new Producto("Laptop Dell", 1200.50, 10);
        Producto mouse = new Producto("Mouse Logitech", 25.99, 50);
        Producto teclado = new Producto("Teclado Mecánico", 89.90, 30);
        save(laptop);
        save(mouse);
        save(teclado);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto findById(String id) {
        return productoRepository.findById(id);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto update(Producto producto) {
        return productoRepository.update(producto);
    }

    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorFiltros(String nombre, Double precioMin, Double precioMax) {
        return productoRepository.buscarPorFiltros(nombre, precioMin, precioMax);
    }
}
