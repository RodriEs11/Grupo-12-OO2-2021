package com.unla.grupo12.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class PermisoPeriodo extends Permiso {

  @Column(name = "cantDias")
  private int cantDias;
  @Column(name = "vacaciones")
  private boolean vacaciones;
  @ManyToOne
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "rodado_id")
  private Rodado rodado;

  public PermisoPeriodo(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta,
		  int cantDias, boolean vacaciones, Rodado rodado) {
	  super(idPermiso, pedido, fecha, desdeHasta);
    this.cantDias = cantDias;
    this.vacaciones = vacaciones;
    this.rodado = rodado;
  }

  public PermisoPeriodo() {
  }

  public int getCantDias() {
    return cantDias;
  }

  public void setCantDias(int cantDias) {
    this.cantDias = cantDias;
  }

  public boolean isVacaciones() {
    return vacaciones;
  }

  public void setVacaciones(boolean vacaciones) {
    this.vacaciones = vacaciones;
  }

  public Rodado getRodado() {
    return rodado;
  }

  public void setRodado(Rodado rodado) {
    this.rodado = rodado;
  }



}
