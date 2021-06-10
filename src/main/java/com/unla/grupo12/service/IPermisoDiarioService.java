package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.model.PermisoDiarioModel;


public interface IPermisoDiarioService {
	
	PermisoDiarioModel agregar(PermisoDiarioModel permisoDiarioModel);
	
	List<PermisoDiario> getAll();
	
	 PermisoDiarioModel findByIdPermiso (int id);
	
	List<PermisoDiarioModel> listPermisoDiario();
}
