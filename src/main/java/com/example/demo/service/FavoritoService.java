package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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

		public Optional<Favorito> findByProductoAndUsuario(int idProducto, int idUsuario) {
			return Optional.ofNullable(favoritoRepository.findByProductoAndUsuario(idProducto, idUsuario));
	}

	    public Favorito findById(int id) {
	        return favoritoRepository.findById(id);
	    }

		public List<Favorito> findByCliente(Integer idUser) {
			return favoritoRepository.findByCliente(idUser);
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
