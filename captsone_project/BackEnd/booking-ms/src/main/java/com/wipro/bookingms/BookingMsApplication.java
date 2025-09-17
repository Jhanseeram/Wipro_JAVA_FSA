package com.wipro.bookingms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.wipro")
@EnableDiscoveryClient
@EntityScan("com.wipro.entity")
@EnableJpaRepositories("com.wipro.repository")
public class BookingMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingMsApplication.class, args);
	}

}
