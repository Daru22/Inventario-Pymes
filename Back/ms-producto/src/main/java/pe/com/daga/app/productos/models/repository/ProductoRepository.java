package pe.com.daga.app.productos.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pe.com.daga.app.common.db.dagafact.models.entity.Producto;

public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {

	@Query("select v from Producto v where v.nombre like %?1%")
	public List<Producto> findByNombre(String term);
}
