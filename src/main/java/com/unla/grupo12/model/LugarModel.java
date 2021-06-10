package com.unla.grupo12.model;

public class LugarModel {

  private int idLugar;
  private String lugar;
  private String codigoPostal;


  public LugarModel(int idLugar, String lugar, String codigoPostal) {
    this.idLugar = idLugar;
    this.lugar = lugar;
    this.codigoPostal = codigoPostal;
  }

  public LugarModel() {
  }

  public int getIdLugar() {
    return idLugar;
  }

  public String getLugar() {
    return lugar;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setIdLugar(int idLugar) {
    this.idLugar = idLugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  @Override
  public String toString() {
    return "Lugar{" +
        "idLugar=" + idLugar +
        ", lugar='" + lugar + '\'' +
        ", codigoPostal='" + codigoPostal + '\'' +
        '}';
  }
}
