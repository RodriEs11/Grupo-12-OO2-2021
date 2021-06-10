package com.unla.grupo12.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unla.grupo12.entity.Perfil;
import lombok.Data;


public class UsuarioModel {

	private Long id;
	private String nombre;
	private String apellido;
	private Long nroDocumento;
	private String tipoDocumento;
	private String email;
	private String usuario;
	private String clave;
	private boolean activo;
	private Perfil perfil;
	
	public UsuarioModel() {}

	public UsuarioModel(Long id, String nombre, String apellido, Long nroDocumento, String tipoDocumento, String email,
			String usuario, String clave, boolean activo, Perfil perfil) {
		
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.email = email;
		this.usuario = usuario;
		this.setClave(clave);
		this.setActivo(true);  //AL MOMENTO DE AGREGAR UN USUARIO SETEA ESTE VALOR COMO TRUE
		this.perfil = perfil;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		this.clave = pe.encode(clave);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	

}
