package pe.com.daga.app.productos.services;

import java.util.List;

import pe.com.daga.app.common.db.dagafact.models.entity.IngresoProducto;
import pe.com.daga.commons.services.CommonService;

public interface IngresoProductoService extends CommonService<IngresoProducto>{

	Boolean ingresoProducto(List<IngresoProducto> ingresoProducto);

	
}
