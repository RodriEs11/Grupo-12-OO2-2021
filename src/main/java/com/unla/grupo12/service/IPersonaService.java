package com.unla.grupo12.service;

import java.util.List;


import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.model.PersonaModel;

public interface IPersonaService {
	
	public PersonaModel agregarPersona(PersonaModel personaModel);
	
	public List<Persona> listAllPersonas();
	
	public PersonaModel findByIdPersona(int id);
	
	public PersonaModel findByDni(long dni);

	public List<PersonaModel> listPersonaModel();
}
