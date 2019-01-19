package com.adndevelopersoftware.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adndevelopersoftware.app.entity.Actor;

/* Interface IActorDao, es quien manipula la base de datos de Actor
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */
public interface IActorDao extends JpaRepository<Actor, Long>{

	/**
	 * Busca al actor en la base de datos por nombre y apellido 
	 * @param nombre, nombre del actor
	 * @param apellido, apellido del actor
	 * @return devuelve el actor si lo encontro
	 */
	Actor findByNombreAndApellido(String nombre, String apellido);
}
