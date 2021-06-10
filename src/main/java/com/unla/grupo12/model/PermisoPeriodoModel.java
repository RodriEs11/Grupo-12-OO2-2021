package com.unla.grupo12.model;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;

public class PermisoPeriodoModel extends PermisoModel {

  private int cantDias;
  private boolean vacaciones;
  private Rodado rodado;

  public PermisoPeriodoModel(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta,
		  int cantDias, boolean vacaciones, Rodado rodado) {
	  super(idPermiso,  pedido,  fecha,  desdeHasta);
    this.cantDias = cantDias;
    this.vacaciones = vacaciones;
    this.rodado = rodado;
  }

  public PermisoPeriodoModel() {
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


  @Override
  public String toString() {
    return "PermisoPeriodo{" +
        "idPermiso=" + idPermiso +
        ", pedido=" + pedido +
        ", fecha=" + fecha +
        ", desdeHasta=" + desdeHasta +
        ", cantDias=" + cantDias +
        ", vacaciones=" + vacaciones +
        ", rodado=" + rodado +
        '}';
  }
}
