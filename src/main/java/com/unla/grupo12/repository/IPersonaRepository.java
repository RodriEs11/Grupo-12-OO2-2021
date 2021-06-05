package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable> {

	//@Query("SELECT p FROM Persona p JOIN FETCH p.permiso WHERE p.persona = (:dni)")
	//Persona findByNombrePersonaYPermiso(@Param("dni") long dni);
	
	Persona findByIdPersona(int id);
	
	Persona findByDni(long dni);
	

}
