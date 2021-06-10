package com.unla.grupo12.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
public class PermisoDiario extends Permiso {

	 @Column(name="motivo")
  private String motivo;

  public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta, String motivo) {
    super(idPermiso, pedido, fecha, desdeHasta);
    this.motivo = motivo;
  }

  public PermisoDiario() {
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
