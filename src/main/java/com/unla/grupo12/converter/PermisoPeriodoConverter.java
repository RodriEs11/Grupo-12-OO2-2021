package com.unla.grupo12.converter;

import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.model.PermisoPeriodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("permisoPeriodoConverter")
public class PermisoPeriodoConverter {

  @Autowired
  private RodadoConverter rodadoConverter;
  
  @Autowired
  private PersonaConverter personaConverter;

  @Autowired
  private LugarConverter lugarConverter;

  public List<PermisoPeriodoModel> listPermisoPeriodoModel(List<PermisoPeriodo> listPermisoPeriodo) {

    List<PermisoPeriodoModel> permisoPeriodoModelList = new ArrayList<PermisoPeriodoModel>();

    for (PermisoPeriodo p : listPermisoPeriodo) {
      PermisoPeriodoModel permisoPeriodoModel = new PermisoPeriodoModel();
      permisoPeriodoModel.setCantDias(p.getCantDias());
      permisoPeriodoModel.setVacaciones(p.isVacaciones());
      permisoPeriodoModel.setRodado(p.getRodado());
      permisoPeriodoModelList.add(permisoPeriodoModel);
    }
    return permisoPeriodoModelList;
  }

  public List<PermisoPeriodo> listPermisoPeriodo(List<PermisoPeriodoModel> listPermisoPeriodoModel) {

    List<PermisoPeriodo> permisoPeriodoList = new ArrayList<PermisoPeriodo>();

    for (PermisoPeriodoModel p : listPermisoPeriodoModel) {
      PermisoPeriodo permisoPeriodo = new PermisoPeriodo();
      permisoPeriodo.setCantDias(p.getCantDias());
      permisoPeriodo.setVacaciones(p.isVacaciones());
      permisoPeriodo.setRodado(p.getRodado());
      permisoPeriodoList.add(permisoPeriodo);
    }
    return permisoPeriodoList;
  }

  public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoPeriodoModel) {

    return new PermisoPeriodo(permisoPeriodoModel.getIdPermiso(), permisoPeriodoModel.getPedido(),
    		permisoPeriodoModel.getFecha(), permisoPeriodoModel.getDesdeHasta(),
    		permisoPeriodoModel.getCantDias(), permisoPeriodoModel.isVacaciones(),
        permisoPeriodoModel.getRodado());
  }

  public PermisoPeriodoModel entityToModel(PermisoPeriodo permisoPeriodo) {

    return new PermisoPeriodoModel(permisoPeriodo.getIdPermiso(),permisoPeriodo.getPedido()
    	    , permisoPeriodo.getFecha(), permisoPeriodo.getDesdeHasta(),
    		permisoPeriodo.getCantDias(), permisoPeriodo.isVacaciones(),
       permisoPeriodo.getRodado());
  }
}
