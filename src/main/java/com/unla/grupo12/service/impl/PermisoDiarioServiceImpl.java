package com.unla.grupo12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoDiarioConverter;
import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.repository.IPermisoDiarioRepository;
import com.unla.grupo12.service.IPermisoDiarioService;


@Service("permisoDiarioService")
public class PermisoDiarioServiceImpl implements IPermisoDiarioService {

	
	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	
	@Override
	public PermisoDiarioModel agregar(PermisoDiarioModel permisoDiarioModel) {
		
		PermisoDiario permiso = permisoDiarioRepository.save(permisoDiarioConverter.modelToEntity(permisoDiarioModel));
		
	
		return permisoDiarioConverter.entityToModel(permiso);
		
	}

	

	
}
