package pe.com.daga.app.productos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.daga.app.common.db.dagafact.models.entity.IngresoProducto;
import pe.com.daga.app.common.db.dagafact.models.entity.Producto;
import pe.com.daga.app.productos.models.repository.IngresoProductoRepository;
import pe.com.daga.app.productos.models.repository.ProductoRepository;
import pe.com.daga.commons.services.CommonServiceImpl;

@Service
public class IngresoProductoServiceImpl extends CommonServiceImpl<IngresoProducto, IngresoProductoRepository>
		implements IngresoProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Override
	@Transactional
	public Boolean ingresoProducto(List<IngresoProducto> ingresoProductos) {
		try {
			for (IngresoProducto iprod : ingresoProductos) {
				Producto productoDb = iprod.getProducto();
				Integer cantidad = iprod.getCantidad();
				Integer stock = productoDb.getStock();
				Integer totalStock = stock + cantidad;
				productoDb.setStock(totalStock);
				productoRepository.save(productoDb);
				this.repository.save(iprod);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
