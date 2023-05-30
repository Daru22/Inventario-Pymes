package pe.com.daga.app.productos.services;

import java.util.List;

import pe.com.daga.app.common.db.dagafact.models.entity.Categoria;
import pe.com.daga.app.common.db.dagafact.models.entity.Producto;
import pe.com.daga.commons.services.CommonService;

public interface ProductoService extends CommonService<Producto>{

	public Iterable<Categoria> findAllCategorias();
	
	public List<Producto> findByNombre(String term);

}
