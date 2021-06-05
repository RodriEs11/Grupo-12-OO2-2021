package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.model.PermisoModel;

public interface IPermisoService {

	public List<PermisoModel> listPermisoModel();
	
	public List<Permiso> listPermisos();
	
	public List<Permiso> traerPermisoxDni(long dni);

}
