package com.unla.grupo12.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.LugarConverter;
//import com.unla.grupo12.converter.LugarConverter;
import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.model.LugarModel;
import com.unla.grupo12.repository.ILugarRepository;
import com.unla.grupo12.service.ILugarService;

@Service("lugarService")
public class LugarServiceImpl implements ILugarService{

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	
	@Override
	public List<Lugar> getAll() {
		return lugarRepository.findAll();
	}

	@Override
	public LugarModel findByIdLugar(int id) {
		return lugarConverter.entityToModel(lugarRepository.findByIdLugar(id));
	}

	@Override
	public List<LugarModel> listLugar() {
		return lugarConverter.listLugarModel(this.getAll());
	}

}
