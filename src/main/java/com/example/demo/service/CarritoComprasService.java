package com.example.demo.service;
import java.util.List;

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
        initSampleData();
    }

    private void initSampleData() {
         CarritoCompras carroCliente1 = new CarritoCompras(111, usuarioService.findById("1"));
         CarritoCompras carroCliente2 = new CarritoCompras(222, usuarioService.findById("2"));
         CarritoCompras carroCliente3 = new CarritoCompras(333, usuarioService.findById("3"));
        save(carroCliente1);
        save(carroCliente2);
        save(carroCliente3);
    }

    public CarritoCompras save(CarritoCompras carritoCompras) {
        return carritoComprasRepository.save(carritoCompras);
    }

    public CarritoCompras findById(Integer id) {
        return carritoComprasRepository.findById(id);
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
    public List<CarritoCompras> buscarPorFiltros(Integer carritoId, String usuario) {
		return  carritoComprasRepository.buscarPorFiltros(carritoId, usuario);
	}
  
}
