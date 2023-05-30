package pe.com.daga.app.cliente.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pe.com.daga.app.cliente.models.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
	
@Query("select c from Cliente c join fetch c.ventas v where v.id=?1")
public Cliente findClienteByVentaId(Long id);

}
