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
        Producto producto1 = new Producto(100, 40,
                70000.00, "Tee Graphic Horizon", "BLANCO",
                "S", "100% Algodón",
                "Camiseta con diseño gráfico en la espalda. " +
                        "\nIdeal para un estilo relajado y moderno. " +
                        "\nComodidad y frescura en una sola prenda.",
                "Hombre", "Camiseta");
        save(producto1);

        Producto producto2 = new Producto(101, 80,
                70000.00, "Tee Graphic Horizon", "BLANCO",
                "M", "Algodón",
                "Camiseta con diseño gráfico en la espalda. " +
                        "\nIdeal para un estilo relajado y moderno. " +
                        "\nComodidad y frescura en una sola prenda.",
                "Hombre", "Camiseta");
        save(producto2);

        Producto producto3 = new Producto(102, 50,
                70000.00, "Tee Graphic Horizon", "BLANCO",
                "L", "Algodón",
                "Camiseta con diseño gráfico en la espalda. " +
                        "\nIdeal para un estilo relajado y moderno. " +
                        "\nComodidad y frescura en una sola prenda.",
                "Hombre", "Camiseta");
        save(producto3);

        Producto producto4 = new Producto(103, 5,
                130900.00, "Denim Street Clasic", "AZUL",
                "M", "Denim",
                "Chaqueta trendy.",
                "Hombre", "Camiseta");
        save(producto4);

        Producto producto5 = new Producto(104, 8,
                130900.00, "Polo Relax Cream", "AZUL",
                "L", "Denim",
                "Estilo urbano que nunca pasa de moda."+
                "\nIdeal para un look casual con actitud.",
                "Hombre", "Camiseta");
        save(producto5);

        Producto producto6 = new Producto(105, 10,
                96000.00, "Polo Relax Cream", "CRUDO",
                "M", "Poliester",
                "Comodidad y frescura en cada detalle."+
                        "\nPerfecto para días relajados con estilo.",
                "Hombre", "Camiseta");
        save(producto6);




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