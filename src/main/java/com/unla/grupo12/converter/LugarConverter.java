package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.model.LugarModel;
import com.unla.grupo12.model.PerfilModel;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("lugarConverter")
public class LugarConverter {

	public Set<LugarModel> listLugarModel(Set<Lugar> listLugar) {

		Set<LugarModel> lugarModelList = new HashSet<>();

		for (Lugar l : listLugar) {
			LugarModel lugarModel = new LugarModel();
			lugarModel.setIdLugar(l.getIdLugar());
			lugarModel.setCodigoPostal(l.getCodigoPostal());
			lugarModel.setLugar(l.getLugar());
			lugarModelList.add(lugarModel);
		}
		return lugarModelList;
	}

	public Set<Lugar> listLugar(Set<LugarModel> listLugar) {

		Set<Lugar> lugarList = new HashSet<>();

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

	public List<LugarModel> listLugar(List<Lugar> listLugar) {
		
		List<LugarModel> lugarModeList = new ArrayList<LugarModel>();

		for (Lugar l : listLugar) {

			LugarModel lugarModel = new LugarModel();
			lugarModel.setIdLugar(l.getIdLugar());
			lugarModel.setCodigoPostal(l.getCodigoPostal());
			lugarModel.setLugar(l.getLugar());
			lugarModeList.add(lugarModel);

		}
		return lugarModeList;
	}

	public List<Lugar> listLugarModel(List<LugarModel> listLugar) {
		List<Lugar> lugarModeList = new ArrayList<Lugar>();

		for (LugarModel l : listLugar) {

			Lugar lugar= new Lugar();
			lugar.setIdLugar(l.getIdLugar());
			lugar.setCodigoPostal(l.getCodigoPostal());
			lugar.setLugar(l.getLugar());
			lugarModeList.add(lugar);

		}
		return lugarModeList;
	}

}
