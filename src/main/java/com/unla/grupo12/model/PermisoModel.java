package com.unla.grupo12.model;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Persona;

public class PermisoModel {

  protected int idPermiso;
  protected Persona pedido;
  protected LocalDate fecha;
  protected List<Lugar> desdeHasta;

  public PermisoModel(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta) {
    this.idPermiso = idPermiso;
    this.pedido = pedido;
    this.fecha = fecha;
    this.desdeHasta = desdeHasta;
  }

  public PermisoModel() {
  }

  public int getIdPermiso() {
    return idPermiso;
  }

  public void setIdPermiso(int idPermiso) {
    this.idPermiso = idPermiso;
  }

  public Persona getPedido() {
    return pedido;
  }

  public void setPedido(Persona pedido) {
    this.pedido = pedido;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public List<Lugar> getDesdeHasta() {
    return desdeHasta;
  }

  public void setDesdeHasta(List<Lugar> desdeHasta) {
    this.desdeHasta = desdeHasta;
  }

  @Override
  public String toString() {
    return "PermisoModel{" +
        "idPermiso=" + idPermiso +
        ", pedido=" + pedido +
        ", fecha=" + fecha +
        ", desdeHasta=" + desdeHasta +
        '}';
  }
}
