package com.wipro.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
	
	@GetMapping("/hello")
	    public String hello() {
	        return "This is open to all.";
	    }

	@GetMapping("/greet")
    public String greet() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/secure-data")
    public String secureData() {
        return "Confidential data only for authenticated users.";
    }
}
