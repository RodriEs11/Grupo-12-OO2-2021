package com.unla.grupo12.model;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Persona;

public class PermisoDiarioModel extends PermisoModel {

  private String motivo;

  public PermisoDiarioModel(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta, String motivo) {
    super(idPermiso, pedido, fecha, desdeHasta);
    this.motivo = motivo;
  }

  public PermisoDiarioModel() {
  }


  public PermisoDiarioModel(String motivo) {
    this.motivo = motivo;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  @Override
  public String toString() {
    return "PermisoDiario{" +
        "motivo='" + motivo + '\'' +
        '}';
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (this.idPermiso ^ (this.idPermiso >>> 32));
	return result;
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	return super.equals(obj);
}
  
  
}
