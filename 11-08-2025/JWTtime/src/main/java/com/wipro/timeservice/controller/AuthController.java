package com.wipro.timeservice.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.timeservice.dto.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("userId") String userId, @RequestParam("password") String password) {
		
		System.out.println("Login attempt - UserId: " + userId + ", Password: " + (password != null ? "[PROVIDED]" : "[NULL]"));
		
		if (userId == null || userId.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			System.out.println("Login failed - Empty credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	
		if ("admin".equals(userId) && "password".equals(password)) {
			String token = generateJWTToken(userId);
			User user = new User();
			user.setUserId(userId);
			user.setToken(token);
			System.out.println("Login successful - Token generated for: " + userId);
			System.out.println("Generated token (first 50 chars): " + token.substring(0, Math.min(50, token.length())) + "...");
			return ResponseEntity.ok(user);
		} else {
			System.out.println("Login failed - Invalid credentials for userId: " + userId);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	private String generateJWTToken(String userId) {
		String secretKey = "timeServiceSecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
 
		String token = Jwts
				.builder()
				.setId("timeServiceApp")
				.setSubject(userId)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000)) 
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
