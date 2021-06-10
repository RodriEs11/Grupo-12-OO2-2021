package com.unla.grupo12.auxiliar;

import java.time.LocalDate;
import java.util.Date;



import org.springframework.format.annotation.DateTimeFormat;

public class Fecha {

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tipoDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFinal;
	
	
	public Fecha() {
		this.tipoDate = new Date();
		this.fechaInicial = new Date();
		this.fechaFinal = new Date();
	}

	
	public Date getTipoDate() {
		return tipoDate;
	}

	public void setTipoDate(Date tipoDate) {
		this.tipoDate = tipoDate;
	}

	
	public Date getFechaInicial() {
		return fechaInicial;
	}


	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	public LocalDate fechaToLocalDate(Date fecha) {
		return LocalDate.of(fecha.getYear()+1900, fecha.getMonth() +1, fecha.getDate());
	}

}
