package pe.com.daga.app.common.db.dagafact.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Long idVenta;
	@NotEmpty
	private String tipoDocumento;
	@NotEmpty
	@Size(min=4,max=30)
	private String numDocumento;
	@NotEmpty
	@Email
	private String correo;
	@JsonIgnoreProperties(value = { "venta" }, allowSetters = true)
	@OneToMany(mappedBy = "venta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> detalleVenta;
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	public Venta() {
		this.detalleVenta = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.fechaCreacion = new Date();
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Venta)) {
			return false;
		}
		Venta v = (Venta) obj;
		return this.idVenta != null && this.idVenta.equals(v.getIdVenta());
	}

	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta.clear();
		detalleVenta.forEach(d -> {
			this.addDetalleVenta(d);
		});
	}

	public void addDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta.add(detalleVenta);
		detalleVenta.setVenta(this);
	}

	public void removeDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta.remove(detalleVenta);
		detalleVenta.setVenta(null);
	}
}
