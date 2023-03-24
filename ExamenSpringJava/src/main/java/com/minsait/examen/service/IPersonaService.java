package com.minsait.examen.service;

import com.minsait.examen.model.BaseResponse;

/**
 * Service encargado de gestionar la lógica para las personas
 * */
public interface IPersonaService {

	/**
	 * Este metodo permite buscar a una persona por el id
	 * @param id
	 * @return {@link BaseResponse}
	 * */
	BaseResponse getPersona(int id);
	
	/**
	 * Este metodo permite obtener la lista de personas
	 * @return {@link BaseResponse}
	 * */
	BaseResponse getPersonas();
	
	/**
	 * Este metodo consume una api publica con restteamplate
	 * @return {@link BaseResponse}
	 * */
	BaseResponse getJokeChuckNorris();
	
	
}
