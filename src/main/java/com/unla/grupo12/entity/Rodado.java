package com.unla.grupo12.entity;


import javax.persistence.*;


@Entity
@Table(name = "rodado")
public class Rodado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idRodado;
  private String dominio;
  private String vehiculo;

  public Rodado(int idRodado, String dominio, String vehiculo) {
    this.idRodado = idRodado;
    this.dominio = dominio;
    this.vehiculo = vehiculo;
  }

  public Rodado() {
  }

	
	public int getIdRodado() {
	return idRodado;
}

public void setIdRodado(int idRodado) {
	this.idRodado = idRodado;
}

	public String getDominio() {
		return dominio;
	}
	
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public String getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

}
