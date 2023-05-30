package pe.com.daga.app.venta.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pe.com.daga.app.common.db.dagafact.models.entity.Venta;

public interface VentaRepository extends PagingAndSortingRepository<Venta, Long>{
	@Query("select v from Venta v where v.tipoDocumento like %?1%")
public List<Venta> findByTipoDoc(String term);
}
