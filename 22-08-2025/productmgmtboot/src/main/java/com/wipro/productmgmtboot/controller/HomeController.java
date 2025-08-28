package com.wipro.productmgmtboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "Welcome to Product Management API! Available endpoints: /api/products";
    }
    
    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
}
