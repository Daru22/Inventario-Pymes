package pe.com.daga.app.productos.models.repository;

import org.springframework.data.repository.CrudRepository;

import pe.com.daga.app.common.db.dagafact.models.entity.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

}
