package com.adndevelopersoftware.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adndevelopersoftware.app.entity.Pelicula;

public interface PeliculaService {

	Pelicula findById(Long id);
	
	Page<Pelicula> findAll(Pageable pageable);

	List<Pelicula> findByNombre(String nombre);
	
	Pelicula save(Pelicula movie);

	Pelicula update(Pelicula movie);

	void delete(Long id);
	
}
