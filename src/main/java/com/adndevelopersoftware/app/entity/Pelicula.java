package com.adndevelopersoftware.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/* Clase Pelicula
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique=true)
	@NotEmpty(message = "La pelicula debe de tener un nombre")
	@Size(min = 1, max = 90, message = "El nombre de la pelicula debe de tener entre 1 y 90 caracteres")
	private String nombre;
	
	@Column(nullable = false)
	@NotEmpty(message = "Falto indicar el pais de origen")
	@Size(min = 1, max = 90, message = "El pais de origen debe de tener entre 1 y 90 caracteres")
	private String paisOrigen;
	
	@NotNull(message = "La fecha de estreno es obligatoria")
	@Temporal(TemporalType.DATE)
	private Date fechaEstreno;
	
	@Column(nullable = false)
	@NotEmpty(message = "La pelicula debe de tener un director")
	@Size(min = 1, max = 90, message = "El nombre del director debe de tener entre 1 y 90 caracteres")
	private String director;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Actor> reparto;

	/**
	 * Getter
	 * @return id de la pelicula, del tipo Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id de la pelicula, aunque se genera de forma automatica debido a la estrategia de generacion, del tipo Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return nombre de la pelicula, del tipo String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter
	 * @param nombre de la pelicula, del tipo String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter
	 * @return pais de origen de la pelicula, del tipo String
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * Setter
	 * @param pais de origen de la pelicula, del tipo String
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * Getter
	 * @return fecha de estreno de la pelicula, del tipo Date
	 */
	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	/**
	 * Setter
	 * @param fecha de estreno de origen de la pelicula, del tipo Date
	 */
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	/**
	 * Getter
	 * @return director de la pelicula, del tipo String
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Setter
	 * @param director de la pelicula, del tipo String
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Getter
	 * @return reparto de la pelicula, es una lista de Actores
	 * @see actores
	 */
	public List<Actor> getReparto() {
		return reparto;
	}

	/**
	 * Setter
	 * @return reparto de la pelicula, es una lista de Actores
	 * @see actores
	 */
	public void setReparto(List<Actor> reparto) {
		this.reparto = reparto;
	}
	
}
