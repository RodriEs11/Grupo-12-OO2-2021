package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Serializable> {

	
	Lugar findByIdLugar(int id);
	Lugar findByLugar(String lugar);
	

}
