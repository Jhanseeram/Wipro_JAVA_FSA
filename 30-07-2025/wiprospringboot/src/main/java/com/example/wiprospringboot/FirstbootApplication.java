package com.example.wiprospringboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wipro"})


public class FirstbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstbootApplication.class, args);
    }
}

