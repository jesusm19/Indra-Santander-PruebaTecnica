package com.minsait.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.examen.model.BaseResponse;
import com.minsait.examen.service.IPersonaService;



/**
 * Clase que contiene los endpoints para gestionar a una personsa
 * */
@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	/**
	 * Se inyecta Service
	 * */
	@Autowired
	private IPersonaService personaService;
	
	
	/**
	 * Este metodo permite buscar una persona a través del id o la lista de todas las personas
	 * @param id valor opcional que representa el id de la persona
	 * @return ResponseEntity<?>
	 * */
	@GetMapping("/getPersonas")
	public ResponseEntity<?> getPersonsa(@RequestParam(required = false) Integer id){
		BaseResponse baseResponse = new BaseResponse();
		//Si id viene como parametro se busca una persona, en caso contrario se obtiene la lista de personas
		if(id != null && id > 0) {
			baseResponse = personaService.getPersona(id);
		} else {
			baseResponse = personaService.getPersonas();
		}
		
		//Se crea la cabecera HttpHeader para regresar tipo de decodificación utf-8
		HttpHeaders head = new HttpHeaders();
		head.add("Content-Type", "application/json;charset=utf-8");
		return new ResponseEntity<BaseResponse>(baseResponse, head,
				baseResponse.getStatus());
	}
	
	@GetMapping("/getJokeChuckNorris")
	public ResponseEntity<?> getJokeChuckNorris(){
		BaseResponse baseResponse = personaService.getJokeChuckNorris();
		
		//Se crea la cabecera HttpHeader para regresar tipo de decodificación utf-8
		HttpHeaders head = new HttpHeaders();
		head.add("Content-Type", "application/json;charset=utf-8");
		return new ResponseEntity<BaseResponse>(baseResponse, head,
				baseResponse.getStatus());
	}

}
