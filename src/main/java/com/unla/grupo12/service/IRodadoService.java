package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.model.RodadoModel;


public interface IRodadoService {
	
	List<Rodado> getAll();
	
	RodadoModel findByIdRodado(int id);
	
	List<Rodado> listRodado();

}

