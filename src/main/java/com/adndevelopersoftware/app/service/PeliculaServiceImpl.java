package com.adndevelopersoftware.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adndevelopersoftware.app.dao.IPeliculaDao;
import com.adndevelopersoftware.app.entity.Pelicula;

/* Clase PeliculaServiceImpl, clase concreta de IPeliculaService
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

@Service
public class PeliculaServiceImpl implements IPeliculaService{

	@Autowired
	private IPeliculaDao dao;
	
	/**
	 * Busca una pelicula por id
	 * @param id, se ingresa el id del tipo long
	 * @return regresa un objeto pelicula si lo encontro, sino devuelve un objeto pelicula null
	 */
	@Override
	@Transactional(readOnly=true)
	public Pelicula findById(Long id) {
		return dao.findById(id).orElse(null);
	}
	
	/**
	 * Busca todas las peliculas sin paginar, ordenadas por nombre de forma ascendente
	 * @return regresa una lista del tipo pelicula con todos los registros
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Pelicula> findAll() {
		return dao.findAllByOrderByNombreAsc();
	}

	/**
	 * Busca todas las peliculas paginadas, ordenadas por nombre de forma ascendente
	 * @return regresa un page del tipo pelicula con todos los registros comprendidos en el page
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<Pelicula> findAll(Pageable pageable) {
		return dao.findAllByOrderByNombreAsc(pageable);
	}

	/**
	 * Busca todas las peliculas que contengan el parametro asignado, ignorando las mayusculas y 
	 * que contengan la/s letra/s que se pasan por parametro.
	 * @param param, del tipo string, es lo que se va la cadena de texto que se va a buscar en el nombre de 
	 * todas las peliculas
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que en nombre
	 * contengan el parametro indicado
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Pelicula> findByNombre(String param) {
		return dao.findByNombreIgnoreCaseOrderByNombre(param);

	}

	/**
	 * Persiste el objeto pelicula 
	 * @param movie, es el objeto pelicula que se va a persistir
	 * @return regresa el objeto pelicula ya persistido
	 */
	@Override
	@Transactional
	public Pelicula save(Pelicula movie) {
		return dao.save(movie);
	}

	/**
	 * elimina el objeto pelicula
	 * @param id, del tipo long, es el id del objeto persistido en la base de datos
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);		
	}

}
