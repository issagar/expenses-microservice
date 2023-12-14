package controlwalletservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ControlWalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlWalletServiceApplication.class, args);
	}

}
