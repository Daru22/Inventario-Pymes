package pe.com.daga.app.cliente.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.daga.app.cliente.models.entity.Cliente;
import pe.com.daga.app.cliente.services.ClienteService;
import pe.com.daga.app.common.db.dagafact.models.entity.Venta;
import pe.com.daga.commons.controllers.CommonController;

@RestController
public class ClienteController extends CommonController<Cliente, ClienteService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Cliente> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.setNombre(cliente.getNombre());
		clienteDb.setApellido(cliente.getApellido());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}

	@PutMapping("/{id}/asignar-venta")
	public ResponseEntity<?> asignarVenta(@RequestBody List<Venta> ventas, @PathVariable Long id) {
		Optional<Cliente> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		ventas.forEach(v -> {
			clienteDb.addVenta(v);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}

	@DeleteMapping("/{id}/eliminar-venta")
	public ResponseEntity<?> eliminarVenta(@RequestBody Venta venta, @PathVariable Long id) {
		Optional<Cliente> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		clienteDb.removeVenta(venta);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}

	@GetMapping("/venta/{id}")
	public ResponseEntity<?> buscarPorVentaId(@PathVariable Long id) {
		Cliente cliente = service.findClienteByVentaId(id);
		return ResponseEntity.ok(cliente);
	}
}
