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

}
