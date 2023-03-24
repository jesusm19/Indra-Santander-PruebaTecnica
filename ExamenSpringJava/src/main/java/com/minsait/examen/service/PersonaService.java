package com.minsait.examen.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.minsait.examen.model.BaseResponse;
import com.minsait.examen.model.Persona;
import com.minsait.examen.repository.IPersonaRepository;


/**
 * Service encargado de gestionar la lógica para las personas
 * */
@Service
public class PersonaService implements IPersonaService {
	
	/** La Constante LOGGER. Obtiene el Logger de la clase */
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonaService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Se inyecta el repositorio
	 * */
	@Autowired
	private IPersonaRepository personaRepository;

	/**
	 * Este metodo permite buscar a una persona por el id
	 * @param id
	 * @return {@link BaseResponse}
	 * */
	@Override
	public BaseResponse getPersona(int id) {
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			//Busca la persona por id
			Persona persona = personaRepository.findById(id).orElse(null);
			if(persona != null) {
				baseResponse.responseOk(persona);
				return baseResponse;
			}
			//Si no la encuentra, regresa mensaje de error
			baseResponse.responseBadRequest(String.format("No se encontro información de la persona con id: %d", id));
			
		} catch (DataAccessException e) {
			LOGGER.error("Error ", e);
			baseResponse.responseBadRequest("Error en base de datos");
		}
		
		return baseResponse;
	}

	/**
	 * Este metodo permite obtener la lista de personas
	 * @return {@link BaseResponse}
	 * */
	@Override
	public BaseResponse getPersonas() {
		BaseResponse baseResponse = new BaseResponse();
		try {
			//Se obtiene la lista personas que se encuentrem
			List<Persona> personas = personaRepository.findAll();
			baseResponse.responseOk(personas);
		} catch (DataAccessException e) {
			LOGGER.error("Error ", e);
			baseResponse.responseBadRequest("Error en base de datos");
		}

		return baseResponse;
	}

	@Override
	public BaseResponse getJokeChuckNorris() {
		BaseResponse baseRespose = new BaseResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<String> st = restTemplate.exchange("https://api.chucknorris.io/jokes/random", HttpMethod.GET,httpEntity, String.class);
		baseRespose.responseOk(st.getBody());
		return baseRespose;
	}
	
}
