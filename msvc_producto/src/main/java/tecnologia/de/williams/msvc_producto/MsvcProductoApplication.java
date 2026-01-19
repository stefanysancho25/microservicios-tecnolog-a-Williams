package tecnologia.de.williams.msvc_producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsvcProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductoApplication.class, args);
	}

}
