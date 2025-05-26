package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.CarritoCompras;
import com.example.demo.repository.CarritoComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CarritoComprasService {
    private final  CarritoComprasRepository carritoComprasRepository;
    private final  UsuarioService usuarioService;

    @Autowired
    public CarritoComprasService( CarritoComprasRepository carritoComprasRepository, UsuarioService usuarioService) {
        this.carritoComprasRepository = carritoComprasRepository;
        this.usuarioService = usuarioService;

    }
    // CRUD methods
    // Create
    public CarritoCompras save(CarritoCompras carritoCompras) {
        return carritoComprasRepository.save(carritoCompras);
    }

    public CarritoCompras findById(Integer id) {
        return carritoComprasRepository.findById(id);
    }

    public CarritoCompras findByIdAndUsuario(Integer id, Integer usuarioId) {
        return carritoComprasRepository.findByIdAndUsuario(id, usuarioId);
    }

    public Optional<CarritoCompras> findByUsuarioId(Integer usuarioId) {
        return carritoComprasRepository.findByUsuarioId(usuarioId);
    }




    public List<CarritoCompras> findAll() {
        return carritoComprasRepository.findAll();
    }

    public CarritoCompras update(CarritoCompras carritoCompras) {
        return carritoComprasRepository.update(carritoCompras);
    }

    public void deleteById(Integer id) {
         carritoComprasRepository.deleteById(id);
    }
  
  
}
