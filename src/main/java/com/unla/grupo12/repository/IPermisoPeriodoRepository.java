package com.unla.grupo12.repository;

import com.unla.grupo12.entity.PermisoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable> {

	PermisoPeriodo findByIdPermiso(int id);
}
