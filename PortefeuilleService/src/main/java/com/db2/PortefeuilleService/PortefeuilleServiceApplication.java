package com.db2.PortefeuilleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PortefeuilleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortefeuilleServiceApplication.class, args);
	}

}
