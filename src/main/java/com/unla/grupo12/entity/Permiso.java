package com.unla.grupo12.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "permiso")
public class Permiso implements java.io.Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int idPermiso;
  @ManyToOne
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "persona_id")
  protected Persona pedido;
  
 
 
  //@DateTimeFormat(pattern = "yyyy-MM-dd")
  //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  //@Temporal(TemporalType.DATE)
  
  protected LocalDate fecha;
 /* @JoinTable(
		  name = "desde_hasta",
		  joinColumns = @JoinColumn(name = "id_lugar", nullable = false),
		  inverseJoinColumns = @JoinColumn(name = "id_permiso")
		  )cascade = CascadeType.ALL*/
  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  protected List<Lugar> desdeHasta;

  public Permiso(int idPermiso, Persona pedido, LocalDate fecha, List<Lugar> desdeHasta) {
    this.idPermiso = idPermiso;
    this.pedido = pedido;
    this.fecha = fecha;
    this.desdeHasta = desdeHasta;
  }

  public Permiso() {
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
    return "Permiso{" +
        "id=" + idPermiso +
        ", pedido=" + pedido +
        ", fecha=" + fecha +
        ", desdeHasta=" + desdeHasta +
        '}';
  }
}
