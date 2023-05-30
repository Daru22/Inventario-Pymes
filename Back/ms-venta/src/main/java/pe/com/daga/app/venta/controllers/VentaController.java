package pe.com.daga.app.venta.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.daga.app.common.db.dagafact.models.entity.DetalleVenta;
import pe.com.daga.app.common.db.dagafact.models.entity.Venta;
import pe.com.daga.app.venta.services.VentaService;
import pe.com.daga.commons.controllers.CommonController;

@RestController
public class VentaController extends CommonController<Venta, VentaService>{
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	@GetMapping("balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String,Object> response = new HashMap<>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Venta venta,BindingResult result,@PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Venta> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Venta ventaDb = o.get();
		ventaDb.setNumDocumento(venta.getNumDocumento());
		ventaDb.setTipoDocumento(venta.getTipoDocumento());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ventaDb));	
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByTipoDoc(term));
	}
	
	@PutMapping("/detalle/{id}")
	public ResponseEntity<?> editarDetalle(@RequestBody Venta venta,@PathVariable Long id) {
		Optional<Venta> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Venta ventaDb = o.get();
		
		List<DetalleVenta> detEliminadas=new ArrayList<>();
		ventaDb.getDetalleVenta().forEach(ddb -> {
			if(!venta.getDetalleVenta().contains(ddb)) {
				detEliminadas.add(ddb);
			};
		});
		
		detEliminadas.forEach(d ->{
			ventaDb.removeDetalleVenta(d);
		});

		ventaDb.setDetalleVenta(venta.getDetalleVenta());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ventaDb));	
	}
}
