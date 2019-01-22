package com.adndevelopersoftware.app.controller.rest;

import com.adndevelopersoftware.app.entity.Pelicula;
import com.adndevelopersoftware.app.service.ActorService;
import com.adndevelopersoftware.app.service.PeliculaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeliculaRestController {

	private final PeliculaService peliculaService;
	private final ActorService actorService;

	public PeliculaRestController(PeliculaService peliculaService, ActorService actorService) {
		this.peliculaService = peliculaService;
		this.actorService = actorService;
	}

	@GetMapping("/peliculas")
	public Page<Pelicula> findAll(Pageable pageable) {
		return peliculaService.findAll(pageable);
	}

	@GetMapping(value = "/peliculas", params = "nombre")
	public List<Pelicula> findByNombre(@RequestParam String nombre) {
		return peliculaService.findByNombre(nombre);
	}

	@PostMapping("/peliculas")
	@ResponseStatus(HttpStatus.CREATED)
	private Pelicula create(@Valid @RequestBody Pelicula pelicula) {
		return peliculaService.save(pelicula);
	}

	@PatchMapping("/peliculas")
	private Pelicula update(@RequestBody Pelicula pelicula) {
		return peliculaService.update(pelicula);
	}

	@DeleteMapping("/peliculas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void delete(@PathVariable Long id) {
		peliculaService.delete(id);
	}
	
}
