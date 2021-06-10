package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.model.LugarModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("lugarConverter")
public class LugarConverter {

  public List<LugarModel> listLugarModel(List<Lugar> list) {

    List<LugarModel> lugarModelList = new ArrayList<LugarModel>();

    for (Lugar l : list) {
      LugarModel lugarModel = new LugarModel();
      lugarModel.setIdLugar(l.getIdLugar());
      lugarModel.setCodigoPostal(l.getCodigoPostal());
      lugarModel.setLugar(l.getLugar());
      lugarModelList.add(lugarModel);
    }
    return lugarModelList;
  }

  public List<Lugar> listLugar(List<LugarModel> listLugar) {

	  List<Lugar> lugarList = new ArrayList<>();

    for (LugarModel l : listLugar) {
      Lugar lugar = new Lugar();
      lugar.setIdLugar(l.getIdLugar());
      lugar.setCodigoPostal(l.getCodigoPostal());
      lugar.setLugar(l.getLugar());
      lugarList.add(lugar);
    }
    return lugarList;
  }

  public Lugar modelToEntity(LugarModel lugarModel) {

    return new Lugar(lugarModel.getIdLugar(), lugarModel.getCodigoPostal(), lugarModel.getLugar());
  }

  public LugarModel entityToModel(Lugar lugar) {

    return new LugarModel(lugar.getIdLugar(), lugar.getCodigoPostal(), lugar.getLugar());
  }


}
