package com.adndevelopersoftware.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adndevelopersoftware.app.dao.IActorDao;
import com.adndevelopersoftware.app.entity.Actor;

/* Clase ActorServiceImpl, clase concreta de IActorService
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

@Service
public class ActorServiceImpl implements IActorService {

	@Autowired
	private IActorDao dao;

	/**
	 * Busca al actor en la base de datos por nombre y apellido 
	 * @param nombre, nombre del actor
	 * @param apellido, apellido del actor
	 * @return devuelve el actor si lo encontro
	 */
	@Override
	@Transactional(readOnly=true)
	public Actor findByNombreAndApellido(String nombre, String apellido) {
		return dao.findByNombreAndApellido(nombre, apellido);
	}

	/**
	 * Guarda al actor en la base de datos
	 * @param actor, se pasa el objeto actor
	 * @return devuelve al objeto guardado
	 */
	@Override
	@Transactional
	public Actor save(Actor actor) {
		return dao.save(actor);
	}

	/**
	 * Verifica si el actor existe en la base de datos.
	 * En caso de encontrar al actor buscado, recupera el objeto y lo devuelve,
	 * caso contrario lo genera y persiste en la base de datos
	 * @param nombre, nombre del actor
	 * @param apellido, apellido del actor
	 * @return actor, devuelve un objeto actor completo
	 */
	@Override
	@Transactional
	public Actor verificaExistenciaActor(String nombre, String apellido) {
		Actor actor = findByNombreAndApellido(nombre, apellido);
		if (actor == null) {
			Actor actorProvisorio = new Actor();
			actorProvisorio.setNombre(nombre);
			actorProvisorio.setApellido(apellido);
			actor = dao.save(actorProvisorio);
		}
		return actor;
	}

}
