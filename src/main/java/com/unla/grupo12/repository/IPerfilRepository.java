package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("perfilRepository")
public interface IPerfilRepository extends JpaRepository<Perfil, Serializable> {

	 Perfil findById(long id);

	 Perfil findByNombre(String nombre);

}
