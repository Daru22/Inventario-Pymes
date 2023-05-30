package pe.com.daga.app.cliente.services;

import pe.com.daga.app.cliente.models.entity.Cliente;
import pe.com.daga.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente> {

	public Cliente findClienteByVentaId(Long id);
}
