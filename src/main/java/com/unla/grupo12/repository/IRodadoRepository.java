package com.unla.grupo12.repository;

import com.unla.grupo12.entity.Rodado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Serializable> {

 Rodado findByDominio(String dominio);
}
