package com.romina.animal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Microservicio animal",
				version = "v1",
				description = "Manejo de los animales en el refugio"
		)
)
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.romina.animal", "mappers"})
public class AnimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalApplication.class, args);
	}

}
