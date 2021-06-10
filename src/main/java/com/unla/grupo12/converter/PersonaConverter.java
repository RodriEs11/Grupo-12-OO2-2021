package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.model.PersonaModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personaConverter")
public class PersonaConverter {

  public List<PersonaModel> listPersonaModel(List<Persona> listPersona) {

    List<PersonaModel> personaModelList = new ArrayList<PersonaModel>();

    for (Persona p : listPersona) {
      PersonaModel personaModel = new PersonaModel();
      personaModel.setIdPersona(p.getIdPersona());
      personaModel.setNombre(p.getNombre());
      personaModel.setApellido(p.getApellido());
      personaModel.setDni(p.getDni());
      personaModelList.add(personaModel);
    }
    return personaModelList;
  }

  public List<Persona> listPersona(List<PersonaModel> listPersona) {

    List<Persona> personaList = new ArrayList<Persona>();

    for (PersonaModel p : listPersona) {
      Persona persona = new Persona();
      persona.setIdPersona(p.getIdPersona());
      persona.setNombre(p.getNombre());
      persona.setApellido(p.getApellido());
      persona.setDni(p.getDni());
      personaList.add(persona);
    }
    return personaList;
  }

  public Persona modelToEntity(PersonaModel personaModel) {

    return new Persona(personaModel.getIdPersona(), personaModel.getNombre(), personaModel.getApellido(), personaModel.getDni());
  }

  public PersonaModel entityToModel(Persona persona) {

    return new PersonaModel(persona.getIdPersona(), persona.getNombre(), persona.getApellido(), persona.getDni());
  }


}
