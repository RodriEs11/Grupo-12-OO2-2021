package com.unla.grupo12.service;

import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.model.PermisoPeriodoModel;

public interface IPermisoPeriodoService {

  PermisoPeriodo permisoPorRodado (String dominio);

  PermisoPeriodoModel agregar(PermisoPeriodoModel permisoPeriodoModel);

}
