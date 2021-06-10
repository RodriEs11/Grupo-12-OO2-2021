package com.unla.grupo12.entity;


import javax.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idLugar;
  private String lugar;
  private String codigoPostal;

  public Lugar(int idLugar, String lugar, String codigoPostal) {
    this.idLugar = idLugar;
    this.lugar = lugar;
    this.codigoPostal = codigoPostal;
  }

  public Lugar() {
  }


  public String getLugar() {
    return lugar;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  

  public int getIdLugar() {
	return idLugar;
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

}
