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
        initSampleData();
    }

    private void initSampleData() {
        Producto producto1 = new Producto(100, 10, 70000.00, "Tee Graphic Horizon", "BLANCO",
                "S", "Algodón",
                "Camiseta con diseño gráfico en la espalda. Ideal para un estilo relajado y moderno. Comodidad y frescura en una sola prenda.",
                "Mujer", "Camiseta");

        save(producto1);

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

    public List<Producto> buscarPorFiltros(String nombre, Double precioMin, Double precioMax) {
        return productoRepository.buscarPorFiltros(nombre, precioMin, precioMax);
    }
}