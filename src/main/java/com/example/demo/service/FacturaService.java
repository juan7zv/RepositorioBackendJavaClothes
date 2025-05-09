package com.example.demo.service;

import com.example.demo.model.Factura;
import com.example.demo.model.Producto;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    // Métodos CRUD
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura findById(Integer id) {
        return facturaRepository.findById(id);
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Factura update(Factura factura) {
        return facturaRepository.update(factura);
    }

    public void deleteById(Integer id) {
        facturaRepository.deleteById(id);
    }

}