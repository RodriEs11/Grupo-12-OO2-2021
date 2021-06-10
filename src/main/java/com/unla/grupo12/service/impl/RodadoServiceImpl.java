package com.unla.grupo12.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.RodadoConverter;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.repository.IRodadoRepository;
import com.unla.grupo12.service.IRodadoService;

@Service("rodadoService")
public class RodadoServiceImpl implements IRodadoService{

	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Override
	public List<Rodado> getAll() {
		return rodadoRepository.findAll(); 
	}

	@Override
	public RodadoModel findByIdRodado(int id) {
		return rodadoConverter.entityToModel(rodadoRepository.findByIdRodado(id));
	}

	@Override
	public List<Rodado> listRodado() {
		// TODO Auto-generated method stub
		return null;
	}

}
