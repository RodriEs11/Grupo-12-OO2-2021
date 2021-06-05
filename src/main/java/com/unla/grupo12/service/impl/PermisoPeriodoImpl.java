package com.unla.grupo12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoPeriodoConverter;
import com.unla.grupo12.converter.UsuarioConverter;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.repository.IPermisoPeriodoRepository;
import com.unla.grupo12.service.IPermisoPeriodoService;


@Service("permisoPeriodoService")
public class PermisoPeriodoImpl implements IPermisoPeriodoService {

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;
	
	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;

	@Override
	public PermisoPeriodo permisoPorRodado(String dominio) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
