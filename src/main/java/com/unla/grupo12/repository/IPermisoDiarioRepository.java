package com.unla.grupo12.repository;

import com.unla.grupo12.entity.PermisoDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable> {

	PermisoDiario findByIdPermiso(int id);
}
