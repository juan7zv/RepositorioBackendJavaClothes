package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Favorito;
import com.example.demo.model.Usuario;
import com.example.demo.repository.FavoritoRepository;
import com.example.demo.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService {
	 private final FavoritoRepository favoritoRepository;
	 private final ProductoService productoService;
	 private final  UsuarioService usuarioService;
	    @Autowired
	    public FavoritoService(FavoritoRepository favoritoRepository, ProductoService productoService, UsuarioService usuarioService) {
	        this.favoritoRepository = favoritoRepository;
	        this.productoService = productoService;
	        this.usuarioService = usuarioService;
	        //initSampleData();
	    }
/*
	    private void initSampleData() {
			Usuario usuario1 = usuarioService.findById(123456);
			Favorito urbanPoloFemenino = new Favorito(1, usuario1, productoService.findById(100));
			save(urbanPoloFemenino);

			Usuario usuario2 = usuarioService.findById(234567);
			Favorito palazzoJean = new Favorito(2, usuario2, productoService.findById(100));
			save(palazzoJean);

			Usuario usuario3 = usuarioService.findById(345678);
	    	Favorito sportVibeRetro = new Favorito(3, usuario3, productoService.findById(100));
			save(sportVibeRetro);

	    } */

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
