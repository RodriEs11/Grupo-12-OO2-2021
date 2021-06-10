package com.unla.grupo12.model;

public class PerfilModel {

	private Long id;
	private String nombre;

	
	public PerfilModel() {}
	
	
	public PerfilModel(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
