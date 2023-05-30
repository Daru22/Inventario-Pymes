package pe.com.daga.app.productos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.daga.app.common.db.dagafact.models.entity.Categoria;
import pe.com.daga.app.common.db.dagafact.models.entity.Producto;
import pe.com.daga.app.productos.models.repository.CategoriaRepository;
import pe.com.daga.app.productos.models.repository.ProductoRepository;
import pe.com.daga.commons.services.CommonServiceImpl;

@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, ProductoRepository> implements ProductoService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Categoria> findAllCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return repository.findByNombre(term);
	}

}
