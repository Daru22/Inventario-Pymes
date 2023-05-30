package pe.com.daga.app.productos.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.com.daga.app.common.db.dagafact.models.entity.IngresoProducto;
import pe.com.daga.app.common.db.dagafact.models.entity.Producto;
import pe.com.daga.app.productos.services.IngresoProductoService;
import pe.com.daga.app.productos.services.ProductoService;
import pe.com.daga.commons.controllers.CommonController;

@RestController
public class ProductoController extends CommonController<Producto, ProductoService> {

	@Autowired
	protected IngresoProductoService ingresoProductoService;
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {

		Optional<Producto> o = service.findById(id);
		if (!o.isPresent() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Producto producto, BindingResult result,
			@PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Producto> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Producto productoDb = o.get();
		productoDb.setNombre(producto.getNombre());
		productoDb.setDescripcion(producto.getDescripcion());
		productoDb.setPrecio(producto.getPrecio());
		productoDb.setStock(producto.getStock());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDb));
	}

	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConfoto(@Valid Producto producto, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if (!archivo.isEmpty()) {
			producto.setFoto(archivo.getBytes());
		}
		return super.crear(producto, result);
	}

	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Producto producto, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Producto> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Producto productoDb = o.get();
		productoDb.setNombre(producto.getNombre());
		productoDb.setDescripcion(producto.getDescripcion());
		productoDb.setPrecio(producto.getPrecio());
		productoDb.setStock(producto.getStock());

		if (!archivo.isEmpty()) {
			productoDb.setFoto(archivo.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDb));
	}

	@GetMapping("/categorias")
	public ResponseEntity<?> listarCategorias() {
		return ResponseEntity.ok(service.findAllCategorias());
	}
	
	@PostMapping("/ingresar-producto")
	public ResponseEntity<?> ingresarProductos(@Valid @RequestBody List<IngresoProducto> ingresoProductos,BindingResult result) {
		
		if (result.hasErrors()) {
			return this.validar(result);
		}

		return ResponseEntity.ok(ingresoProductoService.ingresoProducto(ingresoProductos));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
}
