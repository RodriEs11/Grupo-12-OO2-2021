package com.unla.grupo12.model;

public class RodadoModel {

  private int idRodado;
  private String dominio;
  private String vehiculo;

  public RodadoModel(int idRodado, String dominio, String vehiculo) {
    this.idRodado = idRodado;
    this.dominio = dominio;
    this.vehiculo = vehiculo;
  }

  public RodadoModel() {
  }

  public int getIdRodado() {
    return idRodado;
  }

  public String getDominio() {
    return dominio;
  }

  public String getVehiculo() {
    return vehiculo;
  }

  public void setIdRodado(int idRodado) {
    this.idRodado = idRodado;
  }

  public void setDominio(String dominio) {
    this.dominio = dominio;
  }

  public void setVehiculo(String vehiculo) {
    this.vehiculo = vehiculo;
  }

  @Override
  public String toString() {
    return "RodadoModel{" +
        "idRodado=" + idRodado +
        ", dominio='" + dominio + '\'' +
        ", vehiculo='" + vehiculo + '\'' +
        '}';
  }
}
