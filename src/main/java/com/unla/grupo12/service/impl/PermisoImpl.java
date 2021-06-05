package com.unla.grupo12.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoConverter;
import com.unla.grupo12.converter.UsuarioConverter;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.repository.IPermisoRepository;
import com.unla.grupo12.repository.IUsuarioRepository;
import com.unla.grupo12.service.IPermisoService;

@Service("permisoService")
public class PermisoImpl implements IPermisoService{

	
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;

	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;

	@Override
	public List<PermisoModel> listPermisoModel() {
		List<PermisoModel> permisoList = new ArrayList<PermisoModel>();
		permisoList = permisoConverter.listPermisoModel(permisoRepository.findAll());
		return permisoList;
	}

	@Override
	public List<Permiso> traerPermisoxDni(long dni) {
		List<Permiso> lista = this.listPermisos();
		List<Permiso> listaporDni = new ArrayList<Permiso>();
		for(Permiso permiso : lista) {
			if(permiso.getPedido().getDni() == dni) {
				listaporDni.add(permiso);
			}
		}
		return listaporDni;
	}

	@Override
	public List<Permiso> listPermisos() {
		return permisoRepository.findAll();
	}
	
	

}
