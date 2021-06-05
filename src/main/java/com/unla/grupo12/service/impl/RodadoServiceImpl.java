package com.unla.grupo12.service.impl;

import com.unla.grupo12.converter.RodadoConverter;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.model.RodadoModel;
import com.unla.grupo12.repository.IRodadoRepository;
import com.unla.grupo12.service.IRodadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rodadoService")
public class RodadoServiceImpl implements IRodadoService {

	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository  rodadoRepository;

	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;


	@Override
	public List<Rodado> getAll() {

		return rodadoRepository.findAll();

	}

	@Override
	public RodadoModel agregarRodado(RodadoModel rodadoModel) {

		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));

		return rodadoConverter.entityToModel(rodado);
	}

	@Override
	public Rodado buscar(String dominio) {
		Rodado rodadoResultado = rodadoRepository.findByDominio(dominio);
		return rodadoResultado;
	}
}
