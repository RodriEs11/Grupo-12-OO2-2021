package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.model.RodadoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("rodadoConverter")
public class RodadoConverter {

  public List<RodadoModel> listRodadoModel(List<Rodado> listRodado) {

    List<RodadoModel> rodadoModelList = new ArrayList<RodadoModel>();

    for (Rodado r : listRodado) {
      RodadoModel rodadoModel = new RodadoModel();
      rodadoModel.setIdRodado(r.getIdRodado());
      rodadoModel.setDominio(r.getDominio());
      rodadoModel.setVehiculo(r.getVehiculo());
      rodadoModelList.add(rodadoModel);
    }
    return rodadoModelList;
  }

  public List<Rodado> listRodado(List<RodadoModel> listRodado) {

    List<Rodado> rodadoList = new ArrayList<Rodado>();

    for (RodadoModel r : listRodado) {
      Rodado rodado = new Rodado();
      rodado.setIdRodado(r.getIdRodado());
      rodado.setDominio(r.getDominio());
      rodado.setVehiculo(r.getVehiculo());
      rodadoList.add(rodado);
    }
    return rodadoList;
  }

  public Rodado modelToEntity(RodadoModel rodadoModel) {

    return new Rodado(rodadoModel.getIdRodado(), rodadoModel.getDominio(), rodadoModel.getVehiculo());
  }

  public RodadoModel entityToModel(Rodado rodado) {

    return new RodadoModel(rodado.getIdRodado(), rodado.getDominio(), rodado.getVehiculo());
  }
}
