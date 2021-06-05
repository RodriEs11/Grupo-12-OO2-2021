package com.unla.grupo12.repository;

import com.unla.grupo12.entity.PermisoPeriodo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("periodoRepository")
public interface IPeriodoRepository extends IPermisoRepository<PermisoPeriodo> {

  PermisoPeriodo findByRodadoDominio(String dominio);

}
