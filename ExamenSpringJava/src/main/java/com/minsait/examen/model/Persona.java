package com.minsait.examen.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PERSONAS")
public class Persona implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo para identificador
	 * */
	@Id
	private int id;
	/**
	 * Atributo para el nombre de la persona
	 * */
	private String nombre;
	/**
	 * Atributo para apellidos
	 * */
	private String apellido;
	/**
	 * Atributo para el rfc
	 * */
	private String rfc;
	/**
	 * Atributo para la curp
	 * */
	private String curp;
	/**
	 * Atributo para la edad
	 * */
	private int edad;
	/**
	 * atributo para el sexo
	 * */
	private String sexo;
	/**
	 * Atributo para la nacionalidad
	 * */
	private String nacionalidad;

}
