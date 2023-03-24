package com.minsait.examen.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class BaseResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo que recaba el estatus http de la solicitud
	 */
	private HttpStatus status;
	/**
	 * Atributo que sirve para identificar el estatus de respuesta del servicio
	 */
	private boolean access;
	/**
	 * Atributo que regresa el mensaje del servicio
	 */
	private String mensaje;
	/**
	 * Objeto que regresa de respuesta
	 */
	private Object data;
	
	/**
	 * Metodo que settea al objeto con respuesta OK
	 * @param data es un objeto que se regresa en el servicio
	 * */
	public void responseOk(Object data) {
		this.access = true;
		this.mensaje = "OK";
		this.data = data;
		this.status = HttpStatus.OK;
	}
	
	/**
	 * Metodo que settea al objeto con respuesta BadRequest
	 * @param mensaje es un objeto que se regresa en el servicio
	 * */
	public void responseBadRequest(String mensaje) {
		this.access = false;
		this.mensaje = mensaje;
		this.status = HttpStatus.BAD_REQUEST;
	}

}
