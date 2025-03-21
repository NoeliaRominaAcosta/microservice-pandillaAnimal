package com.pandilla.finances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceFinancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFinancesApplication.class, args);
	}

}
