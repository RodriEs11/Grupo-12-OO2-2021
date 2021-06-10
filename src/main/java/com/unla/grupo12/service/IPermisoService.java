package com.unla.grupo12.service;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.model.PermisoModel;



public interface IPermisoService {
	PermisoModel agregar(PermisoModel permisoModel);

	List<Permiso> getAll();

	PermisoModel findByIdPermiso(int id);
	
	List<PermisoModel> listPermiso();

	List<Permiso> findByFecha(LocalDate primerDia, LocalDate ultimoDia);
}
