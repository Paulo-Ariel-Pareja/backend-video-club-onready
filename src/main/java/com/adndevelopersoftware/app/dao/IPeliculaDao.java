package com.adndevelopersoftware.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adndevelopersoftware.app.entity.Pelicula;

/* Interface IPeliculaDao, es quien manipula la base de datos de Pelicula
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */
public interface IPeliculaDao extends JpaRepository<Pelicula, Long> {

	/**
	 * Busca todas las peliculas sin paginar, ordenadas por nombre de forma ascendente
	 * @return regresa una lista del tipo pelicula con todos los registros
	 */
	List<Pelicula> findAllByOrderByNombreAsc();

	/**
	 * Busca todas las peliculas paginadas, ordenadas por nombre de forma ascendente
	 * @return regresa un page del tipo pelicula con todos los registros comprendidos en el page
	 */
	Page<Pelicula> findAllByOrderByNombreAsc(Pageable pageable);

	/**
	 * Busca todas las peliculas que contengan el parametro asignado, ignorando las mayusculas y 
	 * que contengan la/s letra/s que se pasan por parametro.
	 * @param param, del tipo string, es lo que se va la cadena de texto que se va a buscar en el nombre de 
	 * todas las peliculas
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que en nombre
	 * contengan el parametro indicado
	 */
	@Query("SELECT e FROM Pelicula e WHERE e.nombre LIKE %?1% ORDER BY e.nombre asc")
	public List<Pelicula> findByNombreIgnoreCaseOrderByNombre(String param);
}
