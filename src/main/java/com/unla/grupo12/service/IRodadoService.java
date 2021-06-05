package com.unla.grupo12.service;

import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.model.RodadoModel;

import java.util.List;

public interface IRodadoService {

  List<Rodado> getAll();

  RodadoModel agregarRodado (RodadoModel rodadoModel);

  Rodado buscar (String dominio);

}
