package com.minsait.examen.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.examen.model.BaseResponse;
import com.minsait.examen.model.Persona;
import com.minsait.examen.service.IPersonaService;


class PersonaControllerTest {
	
	/**
	 * Mock mvc
	 */
	@Autowired
	private MockMvc mvc;
	/**
	 * Mock del service
	 */
	@Mock
	private IPersonaService personaService;
	
	@InjectMocks
	private PersonaController personaController;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		MockitoAnnotations.openMocks(this);
	    mvc = MockMvcBuilders.standaloneSetup(personaController).build();
	}

	@Test
	void testGetPersonas1() throws Exception {
		//Given
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.responseOk(new Persona());
		when(personaService.getPersona(anyInt())).thenReturn(baseResponse);
		
		//When
		mvc.perform(get("/persona/getPersonas")
				.param("id", "1")
				.contentType(MediaType.APPLICATION_JSON))
				
				
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.access", is(true)));
		
		verify(personaService).getPersona(anyInt());
	}
	
	@Test
	void testGetPersonas2() throws Exception {
		//Given
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.responseOk(new Persona());
		when(personaService.getPersonas()).thenReturn(baseResponse);
		
		//When
		mvc.perform(get("/persona/getPersonas")
				.contentType(MediaType.APPLICATION_JSON))
				
				
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.access", is(true)));
		
		verify(personaService).getPersonas();
	}
	
	@Test
	void testgetJokeChuckNorris() throws Exception {
		//Given
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.responseOk("Joke");
		when(personaService.getJokeChuckNorris()).thenReturn(baseResponse);
		
		//When
		mvc.perform(get("/persona/getJokeChuckNorris")
				.contentType(MediaType.APPLICATION_JSON))
				
				
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.access", is(true)));
		
		verify(personaService).getJokeChuckNorris();
	}

}
