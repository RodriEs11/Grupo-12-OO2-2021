package com.unla.grupo12.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoDiarioConverter;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.repository.IPermisoDiarioRepository;
import com.unla.grupo12.service.IPermisoDiarioService;

@Service("permisoDiarioService")
public class PermisoDiarioServiceImpl implements IPermisoDiarioService{

	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;

	@Override
	public PermisoDiarioModel agregar(PermisoDiarioModel permisoDiarioModel) {
		PermisoDiario permisoDiario = permisoDiarioRepository.save(permisoDiarioConverter.modelToEntity(permisoDiarioModel));
		return  permisoDiarioConverter.entityToModel(permisoDiario);
	}

	@Override
	public List<PermisoDiario> getAll() {
		return permisoDiarioRepository.findAll();
	}



	@Override
	public List<PermisoDiarioModel> listPermisoDiario() {
		return permisoDiarioConverter.listPermisoDiarioModel(this.getAll());
	}

	@Override
	public PermisoDiarioModel findByIdPermiso(int id) {
		return permisoDiarioConverter.entityToModel(permisoDiarioRepository.findByIdPermiso(id));
	}
}
