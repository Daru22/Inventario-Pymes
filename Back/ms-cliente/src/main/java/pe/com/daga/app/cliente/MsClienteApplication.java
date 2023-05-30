package pe.com.daga.app.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication

@EntityScan({"pe.com.daga.app.common.db.dagafact.models.entity",
		"pe.com.daga.app.cliente.models.entity"})
public class MsClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClienteApplication.class, args);
	}

}
