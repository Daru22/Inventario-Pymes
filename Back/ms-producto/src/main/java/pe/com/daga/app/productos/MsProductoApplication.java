package pe.com.daga.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"pe.com.daga.app.common.db.dagafact.models.entity"})
public class MsProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductoApplication.class, args);
	}

}
