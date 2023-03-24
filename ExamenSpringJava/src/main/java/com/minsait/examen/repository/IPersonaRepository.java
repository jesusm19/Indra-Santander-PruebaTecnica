package com.minsait.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.examen.model.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer>{

}
