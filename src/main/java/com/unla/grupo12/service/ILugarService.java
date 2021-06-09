package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.model.LugarModel;

public interface ILugarService {

	List<LugarModel> listLugar();
	public LugarModel findById(int id);

}
