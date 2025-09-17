package com.wipro.flight_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.wipro")
@EnableJpaRepositories(basePackages = "com.wipro.repository")
@EntityScan(basePackages = "com.wipro.entity")
public class FlightMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightMsApplication.class, args);
	}

}
