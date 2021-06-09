package com.unla.grupo12.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PersonaConverter;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;
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
	public List<Persona> listAllPersonas() {
		return personaRepository.findAll();
	}

	@Override
	public PersonaModel agregarPersona(PersonaModel personaModel) {
		Persona persona = personaRepository.save(personaConverter.modelToEntity(personaModel));
		return personaConverter.entityToModel(persona);
	}

	@Override
	public PersonaModel findByIdPersona(int id) {
		Persona persona = personaRepository.findByIdPersona(id);
		return personaConverter.entityToModel(persona);
	}

	@Override
	public List<PersonaModel> listPersonaModel() {
		List<PersonaModel> personaList = new ArrayList<PersonaModel>();
		personaList = personaConverter.listPersonaModel(personaRepository.findAll());
		return personaList;
	}

	@Override
	public PersonaModel findByDni(long dni) {
		Persona persona = personaRepository.findByDni(dni);
		return personaConverter.entityToModel(persona);
	}
	

	@Override
	public Persona buscar(long dni) {
		Persona personaResultado = personaRepository.findByDni(dni);
		return personaResultado;
	}
	
}
