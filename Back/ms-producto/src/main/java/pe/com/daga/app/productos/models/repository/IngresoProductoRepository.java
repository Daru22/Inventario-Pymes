package pe.com.daga.app.productos.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pe.com.daga.app.common.db.dagafact.models.entity.IngresoProducto;

public interface IngresoProductoRepository extends PagingAndSortingRepository<IngresoProducto, Long>{

}
