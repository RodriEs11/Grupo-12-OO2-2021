package com.unla.grupo12.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoPeriodoConverter;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.repository.IPermisoPeriodoRepository;
import com.unla.grupo12.service.IPermisoPeriodoService;

@Service("permisoPeriodoService")
public class PermisoPeriodoServiceImpl implements IPermisoPeriodoService{

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;
	
	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
	
	@Override
	public List<PermisoPeriodo> getAll() {
		return permisoPeriodoRepository.findAll();
	}

	@Override
	public PermisoPeriodoModel findByIdPermiso(int id) {
		return permisoPeriodoConverter.entityToModel(permisoPeriodoRepository.findByIdPermiso(id));
	}

	@Override
	public List<PermisoPeriodoModel> listPermisoPeriodo() {
		return permisoPeriodoConverter.listPermisoPeriodoModel(this.getAll());
	}

	@Override
	public PermisoPeriodoModel agregar(PermisoPeriodoModel permisoPeriodoModel) {
		PermisoPeriodo permisoPeriodo = permisoPeriodoRepository.save(permisoPeriodoConverter.modelToEntity(permisoPeriodoModel));
		return permisoPeriodoConverter.entityToModel(permisoPeriodo);
	}

}
