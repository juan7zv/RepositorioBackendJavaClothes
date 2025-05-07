package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }



    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto findById(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto update(Producto producto) {
        return productoRepository.update(producto);
    }

    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

   /* public List<Producto> buscarPorFiltros(String nombre, Double precioMin, Double precioMax) {
        return productoRepository.buscarPorFiltros(nombre, precioMin, precioMax);
    } */
}