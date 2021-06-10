package com.unla.grupo12.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PersonaConverter;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.model.PersonaModel;
import com.unla.grupo12.repository.IPersonaRepository;
import com.unla.grupo12.service.IPersonaService;

@Service("personaService")
public class PersonaServiceImpl implements IPersonaService{

	@Autowired  
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Override
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}

	@Override
	public PersonaModel findByIdPersona(int id) {
		Persona persona  = personaRepository.findByIdPersona(id);
		return personaConverter.entityToModel(persona);	
	}
	

	@Override
	public List<PersonaModel> listPermiso() {
		// TODO Auto-generated method stub
		return null;
	}

}
