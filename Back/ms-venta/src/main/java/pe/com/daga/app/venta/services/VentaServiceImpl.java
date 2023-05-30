package pe.com.daga.app.venta.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.daga.app.common.db.dagafact.models.entity.Venta;
import pe.com.daga.app.venta.models.repository.VentaRepository;
import pe.com.daga.commons.services.CommonServiceImpl;

@Service
public class VentaServiceImpl extends CommonServiceImpl<Venta, VentaRepository> implements VentaService{

	@Override
	@Transactional(readOnly = true)
	public List<Venta> findByTipoDoc(String term) {

		return repository.findByTipoDoc(term);
	}

}
