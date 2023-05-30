package pe.com.daga.app.common.db.dagafact.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ingreso_producto")
public class IngresoProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@NotNull
	private int cantidad;
	private float precioCompra;
	private Date fechaCreacion;
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	@JoinColumn(name="id_producto")
	private Producto producto;
	
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

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
