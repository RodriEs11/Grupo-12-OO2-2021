package com.unla.grupo12.model;

import java.time.LocalDate;
import java.util.Set;

import com.unla.grupo12.entity.Lugar;

public class PermisoPeriodoModel extends PermisoModel {

  private int cantDias;
  private boolean vacaciones;
  private RodadoModel rodado;

  
  public PermisoPeriodoModel(int idPermiso, PersonaModel pedido, LocalDate fecha, int cantDias, boolean vacaciones, RodadoModel rodado) {
	  	super(idPermiso, pedido, fecha );
	    this.cantDias = cantDias;
	    this.vacaciones = vacaciones;
	    this.rodado = rodado;
	  }

  public PermisoPeriodoModel() {}
 
  

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

  public RodadoModel getRodado() {
    return rodado;
  }

  public void setRodado(RodadoModel rodado) {
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
