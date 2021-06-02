package com.unla.grupo12.service;

import com.unla.grupo12.entity.PermisoPeriodo;

import java.util.List;

public interface IPermisoPeriodoService {

  List<PermisoPeriodo> permisoPorRodado (String dominio);

}
