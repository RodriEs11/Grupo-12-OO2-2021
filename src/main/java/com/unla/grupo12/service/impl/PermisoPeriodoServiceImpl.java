package com.unla.grupo12.service.impl;

import com.unla.grupo12.converter.PermisoPeriodoConverter;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.repository.IPeriodoRepository;
import com.unla.grupo12.service.IPermisoPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("permisoPeriodoService")
public class PermisoPeriodoServiceImpl implements IPermisoPeriodoService {


	@Autowired
	@Qualifier("periodoRepository")
	private IPeriodoRepository iPeriodoRepository;

	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;


  @Override
  public List<PermisoPeriodo> permisoPorRodado(String dominio) {

    List <PermisoPeriodo> permisoPeriodoList = iPeriodoRepository.findByRodadoDominio(dominio);

    return permisoPeriodoList;
  }
}
