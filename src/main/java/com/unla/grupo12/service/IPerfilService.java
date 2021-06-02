package com.unla.grupo12.service;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.model.PerfilModel;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IPerfilService {

	PerfilModel agregar(PerfilModel perfilModel);

	List<Perfil> getAll();

	PerfilModel modificar(PerfilModel perfilModel) throws Exception;

	PerfilModel findById(Long id);
	
	PerfilModel findByNombre(String name);

	boolean darDeBaja(Long id) throws Exception;

	List<PerfilModel> listPerfil();

	ByteArrayInputStream generacionPdf();

}
