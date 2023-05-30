package pe.com.daga.app.cliente.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.daga.app.cliente.models.entity.Cliente;
import pe.com.daga.app.cliente.models.repository.ClienteRepository;
import pe.com.daga.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService {

	@Override
	@Transactional(readOnly = true)
	public Cliente findClienteByVentaId(Long id) {
		return repository.findClienteByVentaId(id);
	}

}
