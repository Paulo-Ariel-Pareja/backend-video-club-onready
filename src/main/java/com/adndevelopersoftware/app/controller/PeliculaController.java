package com.adndevelopersoftware.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adndevelopersoftware.app.entity.Actor;
import com.adndevelopersoftware.app.entity.Pelicula;
import com.adndevelopersoftware.app.service.IActorService;
import com.adndevelopersoftware.app.service.IPeliculaService;

/* Interface IActorDao, es quien manipula la base de datos de Actor
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

@RestController
@RequestMapping("/api")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IActorService actorService;

	/**
	 * Busca todas las peliculas, metodo no indicado en el ejercicio propuesto. Utilizar metodo GET
	 * @see http://localhost:8080/api/peliculas
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que esten en la base de datos
	 */
	@GetMapping("/peliculas")
	public List<Pelicula> verPeliculas() {
		return peliculaService.findAll();
	}

	/**
	 * Busca todas las peliculas, utilizar metodo GET
	 * @see http://localhost:8080/api/peliculas/{page}
	 * @param page, es del tipo Integer, indica la pagina a mostrar del paginador
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que esten en el page indicado
	 * para el ejercicio propuesto se dejo el valor de 5 peliculas por pagina
	 */
	@GetMapping("/peliculas/{page}")
	public Page<Pelicula> verPeliculasPaginado(@PathVariable Integer page) {
		return peliculaService.findAll(PageRequest.of(page, 5));
	}

	/**
	 * Busca todas las peliculas, utilizar metodo GET
	 * @see http://localhost:8080/api/peliculas/buscar?param={param}
	 * @param param, es del tipo string, indica el valor a buscar en los nombres de las peliculas
	 * @return regresa una lista del tipo pelicula con todos los elementos pelicula que coincidan con el 
	 * parametro indicado
	 */
	@GetMapping("/peliculas/buscar")
	public List<Pelicula> buscarPeliculas(@RequestParam String param) {
		return peliculaService.findByNombre(param);
	}

	/**
	 * Guarda una pelicula, utilizar metodo POST
	 * @see http://localhost:8080/api/peliculas
	 * @param pelicula, es del tipo Pelicula, es el objeto a persistir
	 * @param result, es el resultado de las validaciones internas con la clase, verifica que los campos
	 * sean del tipo correcto y que cumplan con los requisitos indicados en la clase entity de Pelicula
	 * @return regresa un HashMap pudiendo contener:
	 * key pelicula: el objeto persistido
	 * key mensaje: varia dependiendo de si la operacion se realizo correctamente o tuvo un error
	 * key errors: contiene los errores
	 * @return devuelve codigo Http 201, 400 o 500
	 */
	@PostMapping("/peliculas")
	private ResponseEntity<?> crearPelicula(@Valid @RequestBody Pelicula pelicula, BindingResult result) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Error en '" + err.getField() + "': " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Pelicula peliculaNueva = null;
		List<Actor> listaNueva = new ArrayList<>();
		for(Actor a : pelicula.getReparto()) {
			Actor actor = actorService.verificaExistenciaActor(a.getNombre(), a.getApellido());
			listaNueva.add(actor);
		}

		try {
			pelicula.setReparto(listaNueva);
			peliculaNueva = peliculaService.save(pelicula);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error registrar la pelicula.");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Pelicula agregada al catalogo.");
		response.put("pelicula", peliculaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Actualiza una pelicula, utilizar metodo PUT
	 * @see http://localhost:8080/api/peliculas/{idPelicula}
	 * @param pelicula, es del tipo Pelicula, es el objeto a persistir
	 * @param result, es el resultado de las validaciones internas con la clase, verifica que los campos
	 * sean del tipo correcto y que cumplan con los requisitos indicados en la clase entity de Pelicula
	 * @param idPelicula, es el id del objeto pelicula a persistir
	 * @return regresa un HashMap pudiendo contener:
	 * key pelicula: el objeto actualizado
	 * key mensaje: varia dependiendo de si la operacion se realizo correctamente o tuvo un error
	 * key errors: contiene los errores
	 * @return devuelve codigo Http 201, 400 o 500
	 */
	@PutMapping("/peliculas/{idPelicula}")
	private ResponseEntity<?> actualizarPelicula(@Valid @RequestBody Pelicula pelicula, BindingResult result,
			@PathVariable Long idPelicula) {
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Error en '" + err.getField() + "': " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Pelicula peliculaEncontrada = peliculaService.findById(idPelicula);

		if (peliculaEncontrada == null) {
			response.put("mensaje", "No se encontro la pelicula buscada.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Pelicula peliculaActualizada = null;

		try {
			peliculaEncontrada.setNombre(pelicula.getNombre());
			peliculaEncontrada.setPaisOrigen(pelicula.getPaisOrigen());
			peliculaEncontrada.setFechaEstreno(pelicula.getFechaEstreno());
			peliculaEncontrada.setDirector(pelicula.getDirector());
			peliculaEncontrada.setReparto(pelicula.getReparto());
			peliculaActualizada = peliculaService.save(peliculaEncontrada);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la pelicula.");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La pelicula se actualizo.");
		response.put("pelicula", peliculaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Elimina una pelicula, utilizar metodo DELETE
	 * @see http://localhost:8080/api/peliculas/{idPelicula}
	 * @param idPelicula, es el id del objeto pelicula a eliminar
	 * @return regresa un HashMap pudiendo contener:
	 * key mensaje: varia dependiendo de si la operacion se realizo correctamente o tuvo un error
	 * key errors: contiene los errores
	 * @return devuelve codigo Http 200 o 500
	 */
	@DeleteMapping("/peliculas/{idPelicula}")
	private ResponseEntity<?> eliminarPelicula(@PathVariable Long idPelicula) {
		Map<String, Object> response = new HashMap<>();

		try {
			peliculaService.delete(idPelicula);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar al cliente.");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La pelicula se elimino.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
