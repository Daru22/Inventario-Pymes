package pe.com.daga.app.venta.services;

import java.util.List;

import pe.com.daga.app.common.db.dagafact.models.entity.Venta;
import pe.com.daga.commons.services.CommonService;

public interface VentaService extends CommonService<Venta>{
	public List<Venta> findByTipoDoc(String term);
}
