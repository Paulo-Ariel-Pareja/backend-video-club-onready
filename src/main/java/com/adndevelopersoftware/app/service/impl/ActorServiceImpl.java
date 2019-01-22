package com.adndevelopersoftware.app.service.impl;

import com.adndevelopersoftware.app.dao.ActorDao;
import com.adndevelopersoftware.app.service.ActorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adndevelopersoftware.app.entity.Actor;

@Service
public class ActorServiceImpl implements ActorService {

	private final ActorDao actorDao;

	public ActorServiceImpl(ActorDao actorDao) {
		this.actorDao = actorDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Actor findByNombreAndApellido(String nombre, String apellido) {
		return actorDao.findByNombreAndApellido(nombre, apellido);
	}

	@Override
	@Transactional
	public Actor save(Actor actor) {
		return actorDao.save(actor);
	}

	@Override
	@Transactional
	public Actor verificaExistenciaActor(String nombre, String apellido) {
		Actor actor = findByNombreAndApellido(nombre, apellido);
		if (actor == null) {
			Actor actorProvisorio = new Actor();
			actorProvisorio.setNombre(nombre);
			actorProvisorio.setApellido(apellido);
			actor = actorDao.save(actorProvisorio);
		}
		return actor;
	}

}
