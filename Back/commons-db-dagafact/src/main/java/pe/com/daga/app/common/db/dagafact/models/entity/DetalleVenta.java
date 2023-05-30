package pe.com.daga.app.common.db.dagafact.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="detalle_ventas")
public class DetalleVenta{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_detalle_venta")
	private Long idDetalleVenta;
	private Long idProducto;
	@NotEmpty
	private int cantidad;
	private float precioUnitario;
	private float descuento;
	
	@JsonIgnoreProperties(value= {"detalleVenta"})
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	@JoinColumn(name="venta_id")
	private Venta venta;

	public Long getIdidDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdidDetalleVenta(Long id) {
		this.idDetalleVenta = id;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DetalleVenta)) {
			return false;
		}
		DetalleVenta d = (DetalleVenta) obj;
		return this.idDetalleVenta != null && this.idDetalleVenta.equals(d.getIdidDetalleVenta());
	}

}
