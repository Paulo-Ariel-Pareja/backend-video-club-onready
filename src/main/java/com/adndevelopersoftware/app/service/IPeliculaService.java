package com.adndevelopersoftware.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adndevelopersoftware.app.entity.Pelicula;

/* Interface IPeliculaService
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

public interface IPeliculaService {

	/**
	 * Busca una pelicula por id
	 * @param id, se ingresa el id del tipo long
	 * @return regresa un objeto pelicula si lo encontro
	 */
	public Pelicula findById(Long id);
	
	/**
	 * Busca todas las peliculas sin paginar, ordenadas por nombre de forma ascendente
	 * @return regresa una lista del tipo pelicula con todos los registros
	 */
	public List<Pelicula> findAll();
	
	/**
	 * Busca todas las peliculas paginadas, ordenadas por nombre de forma ascendente
	 * @return regresa un page del tipo pelicula con todos los registros comprendidos en el page
	 */
	public Page<Pelicula> findAll(Pageable pageable);
	
	/**
	 * Busca todas las peliculas que contengan el parametro asignado, ignorando las mayusculas y 
	 * que contengan la/s letra/s que se pasan por parametro.
	 * @param param, del tipo string, es lo que se va la cadena de texto que se va a buscar en el nombre de 
	 * todas las peliculas
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que en nombre
	 * contengan el parametro indicado
	 */
	public List<Pelicula> findByNombre(String param);
	
	/**
	 * Persiste el objeto pelicula 
	 * @param movie, es el objeto pelicula que se va a persistir
	 * @return regresa el objeto pelicula ya persistido
	 */
	public Pelicula save(Pelicula movie);
	
	/**
	 * elimina el objeto pelicula
	 * @param id, del tipo long, es el id del objeto persistido en la base de datos
	 */
	public void delete(Long id);
	
}
