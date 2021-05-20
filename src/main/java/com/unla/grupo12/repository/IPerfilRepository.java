package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Perfil;
import com.unla.grupo12.entity.Usuario;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("perfilRepository")
public interface IPerfilRepository extends JpaRepository<Perfil, Serializable> {

	public abstract Perfil findById(long id);

	public abstract Perfil findByNombre(String nombre);

}