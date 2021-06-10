package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.model.PersonaModel;

public interface IPersonaService {

	
	public List<Persona> getAll();
	
	public PersonaModel findByIdPersona(int id);
	
	public List<PersonaModel> listPermiso();
}
