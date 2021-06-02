package com.unla.grupo12.controller;

import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.service.IPermisoPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

  @Autowired
  @Qualifier("permisoPeriodoService")
  private IPermisoPeriodoService permisoPeriodoService;

  @PreAuthorize("hasAuthority('Auditoria')")
  @GetMapping("/buscar/{patente}")
  public List<PermisoPeriodo> buscarRodado(@PathVariable("dominio") String dominio) {

    List<PermisoPeriodo> listaDePermisos = permisoPeriodoService.permisoPorRodado(dominio);

    return listaDePermisos;
  }


}