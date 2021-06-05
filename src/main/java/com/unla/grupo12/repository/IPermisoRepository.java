package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Rodado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("permiso")
public interface IPermisoRepository<T extends Permiso> extends JpaRepository<T, Serializable> {
	
	Permiso findByIdPermiso(int id);

}
