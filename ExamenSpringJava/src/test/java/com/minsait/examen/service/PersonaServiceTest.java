package com.minsait.examen.service;

import static org.mockito.ArgumentMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.minsait.examen.model.BaseResponse;
import com.minsait.examen.model.Persona;
import com.minsait.examen.repository.IPersonaRepository;

class PersonaServiceTest {
	
	/**
	 * Mock Service
	 * */
	@Mock
	private IPersonaRepository personaRepository;
	
	/**
	 * Mock restTemplate
	 * */
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private PersonaService personaService;
	
	private List<Persona> personas;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		personas = new ArrayList<>();
		Persona p1 = new Persona();
		p1.setId(1);
		p1.setNombre("Jose");
		
		Persona p2 = new Persona();
		p2.setId(2);
		p2.setNombre("Jorg");
		personas.add(p1);
		personas.add(p2);
	}

	@Test
	void testGetPersona1() {
		Optional<Persona> optional = Optional.of(personas.get(0));
		//Given
		when(personaRepository.findById(anyInt())).thenReturn(optional);
		
		//When
		BaseResponse baseResponse = personaService.getPersona(1);
		//Then
		assertThat(baseResponse.isAccess()).isTrue();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.OK);
		assertThat(baseResponse.getData()).isNotNull();
		
		verify(personaRepository).findById(anyInt());
	}
	
	@Test
	void testGetPersona2() {
		Optional<Persona> optional = Optional.empty();
		//Given
		when(personaRepository.findById(anyInt())).thenReturn(optional);
		
		//When
		BaseResponse baseResponse = personaService.getPersona(1);
		//Then
		assertThat(baseResponse.isAccess()).isFalse();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(baseResponse.getData()).isNull();
		
		verify(personaRepository).findById(anyInt());
	}
	
	@Test
	void testGetPersona3() {
		//Given
		doThrow(new DataAccessException("") {
			private static final long serialVersionUID = 1L;
		}).when(personaRepository).findById(anyInt());
		
		//When
		BaseResponse baseResponse = personaService.getPersona(1);
		//Then
		assertThat(baseResponse.isAccess()).isFalse();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(baseResponse.getData()).isNull();
		
		verify(personaRepository).findById(anyInt());
	}
	
	@Test
	void testGetPersonas1() {
		//Given
		when(personaRepository.findAll()).thenReturn(personas);
		
		//When
		BaseResponse baseResponse = personaService.getPersonas();
		//Then
		assertThat(baseResponse.isAccess()).isTrue();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.OK);
		assertThat(baseResponse.getData()).isNotNull();
		
		verify(personaRepository).findAll();
	}
	
	
	@Test
	void testGetPersonas2() {
		//Given
		doThrow(new DataAccessException("") {
			private static final long serialVersionUID = 1L;
		}).when(personaRepository).findAll();
		
		//When
		BaseResponse baseResponse = personaService.getPersonas();
		//Then
		assertThat(baseResponse.isAccess()).isFalse();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(baseResponse.getData()).isNull();
		
		verify(personaRepository).findAll();
	}
	
	@Test
	void testGetJokeChuckNorris() {
		//Given
		ResponseEntity<String> responseEntity = new ResponseEntity<>("A good Joke about Chuck Norris", HttpStatus.OK);
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any())).thenReturn(responseEntity);
		
		//when
		BaseResponse baseResponse = personaService.getJokeChuckNorris();
		String joke = (String) baseResponse.getData();
		//Then
		assertThat(baseResponse.isAccess()).isTrue();
		assertThat(baseResponse.getStatus()).isEqualTo(HttpStatus.OK);
		assertThat(joke).isEqualTo("A good Joke about Chuck Norris");
	}

}
