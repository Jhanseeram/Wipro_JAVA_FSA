package com.wipro.productmgmtboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
    
    @GetMapping("/")
    public String home() {
        return "Spring Boot Application is Running!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }
}
