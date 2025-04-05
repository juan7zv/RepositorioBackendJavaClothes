package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Favorito;
import com.example.demo.model.Producto;
import com.example.demo.repository.FavoritoRepository;
import com.example.demo.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService {
	 private final FavoritoRepository favoritoRepository;
	 private final ProductoRepository productoRepository;
	 private final  UsuarioService usuarioService;
	    @Autowired
	    public FavoritoService(FavoritoRepository favoritoRepository, ProductoRepository productoRepository, UsuarioService usuarioService) {
	        this.favoritoRepository = favoritoRepository;
	        this.productoRepository = productoRepository;
	        this.usuarioService = usuarioService;
	        initSampleData();
	    }

	    private void initSampleData() {
	    	Favorito urbanPoloFemenino = new Favorito(1, usuarioService.findById("1"), productoRepository.findById("1"));
	    	Favorito palazzoJean = new Favorito(2, usuarioService.findById("2"), productoRepository.findById("2"));
	    	Favorito sportVibeRetro = new Favorito(3, usuarioService.findById("3"), productoRepository.findById("3"));
	        save(urbanPoloFemenino);
	        save(palazzoJean);
	        save(sportVibeRetro);
	    }

	    public Favorito save(Favorito favorito) {
	        return favoritoRepository.save(favorito);
	    }

	    public Favorito findById(int id) {
	        return favoritoRepository.findById(id);
	    }

	    public List<Favorito> findAll() {
	        return favoritoRepository.findAll();
	    }

	    public Favorito update(Favorito favorito) {
	        return favoritoRepository.update(favorito);
	    }

	    public void deleteById(int id) {
	    	favoritoRepository.deleteById(id);
	    }

	    public List<Favorito> buscarPorFiltros(int idFavorito, String usuario, String producto) {
	        return favoritoRepository.buscarPorFiltros(idFavorito, usuario, producto);
	    }

}
