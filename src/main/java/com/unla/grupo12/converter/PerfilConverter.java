package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.model.PerfilModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("perfilConverter")
public class PerfilConverter {

	 public List<PerfilModel> listPerfil(List<Perfil> listPerfil) {

		    List<PerfilModel> perfilModelList = new ArrayList<PerfilModel>();

		    for (Perfil p : listPerfil) {
		      PerfilModel perfilModel = new PerfilModel();
		      perfilModel.setId(p.getId());
		      perfilModel.setNombre(p.getNombre());
		      perfilModelList.add(perfilModel);
		    }

		    return perfilModelList;
		  }

		  public List<Perfil> listPerfilModel(List<PerfilModel> listPerfil) {

		    List<Perfil> perfilList = new ArrayList<Perfil>();

		    for (PerfilModel p : listPerfil) {
		      Perfil perfil = new Perfil();
				perfil.setId(p.getId());
		      perfil.setNombre(p.getNombre());
		      perfilList.add(perfil);
		    }

		    return perfilList;
		  }
	
	public Perfil modelToEntity(PerfilModel perfilModel) {

		return new Perfil(perfilModel.getId(), perfilModel.getNombre());
	}

	public PerfilModel entityToModel(Perfil perfil) {

		return new PerfilModel(perfil.getId(), perfil.getNombre());
	}


}
