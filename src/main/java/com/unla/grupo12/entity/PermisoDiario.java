package com.unla.grupo12.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "permiso_diario")
public class PermisoDiario extends Permiso {

  private String motivo;

  
  public PermisoDiario() {
  }
  
  public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
    super(idPermiso, pedido, fecha, desdeHasta);
    this.motivo = motivo;
  }

  

  public PermisoDiario(String motivo) {
    this.motivo = motivo;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

}
