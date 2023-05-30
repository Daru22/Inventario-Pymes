package pe.com.daga.app.cliente.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import pe.com.daga.app.common.db.dagafact.models.entity.Venta;

@Entity
@Table(name="clientes")
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty
private String nombre;
private String apellido;
@Column(name="fecha_creacion")
@Temporal(TemporalType.TIMESTAMP)
private Date fechaCreacion;
@OneToMany
private List<Venta> ventas;

public Cliente() {
	this.ventas=new ArrayList<>();
}

@PrePersist
public void prePersist() {
	this.fechaCreacion=new Date();
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
public Date getFechaCreacion() {
	return fechaCreacion;
}
public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}

public List<Venta> getVentas() {
	return ventas;
}

public void setVentas(List<Venta> ventas) {
	this.ventas = ventas;
}

public void addVenta(Venta venta) {
	this.ventas.add(venta);
}

public void removeVenta(Venta venta) {
	this.ventas.remove(venta);
}
}
