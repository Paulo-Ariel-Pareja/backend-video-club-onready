package com.adndevelopersoftware.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/* Clase Actor
 * @author Paulo Ariel Pareja
 * @version 18.01.2019
 */

@Entity
@Table(name = "actores", indexes = { @Index(name = "nombre_apellido", columnList = "nombre, apellido", unique = true) })
public class Actor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "Falto indicar el nombre del actor")
	@Size(min = 1, max = 90, message = "El nombre debe de contener entre 1 y 90 caracteres")
	private String nombre;

	@Column(nullable = false)
	@NotEmpty(message = "Falto indicar el apellido del actor")
	@Size(min = 1, max = 90, message = "El apellido debe de contener entre 1 y 90 caracteres")
	private String apellido;

	/**
	 * Getter
	 * @return id del actor, del tipo Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter
	 * @param id del actor, aunque se genera de forma automatica debido a la estrategia de generacion, del tipo Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return nombre del actor, del tipo String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter
	 * @param nombre del actor, del tipo String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter
	 * @return apellido del actor, del tipo String
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setter
	 * @param apellido del actor, del tipo String
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
