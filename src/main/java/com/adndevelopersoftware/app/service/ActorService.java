package com.adndevelopersoftware.app.service;

import com.adndevelopersoftware.app.entity.Actor;

/* Interface ActorServiceImpl
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

public interface ActorService {
			
	/**
	 * Busca al actor en la base de datos por nombre y apellido 
	 * @param nombre, nombre del actor
	 * @param apellido, apellido del actor
	 */
	public Actor findByNombreAndApellido(String nombre, String apellido);
	
	/**
	 * Guarda al actor en la base de datos
	 * @param actor, se pasa el objeto actor
	 */
	public Actor save(Actor actor);
	
	/**
	 * Verifica si el actor existe en la base de datos.
	 * En caso de encontrar al actor buscado, recupera el objeto y lo devuelve,
	 * caso contrario lo genera y persiste en la base de datos
	 * @param nombre, nombre del actor
	 * @param apellido, apellido del actor
	 * @return actor, devuelve un objeto actor completo
	 */
	public Actor verificaExistenciaActor(String nombre, String apellido);

}
