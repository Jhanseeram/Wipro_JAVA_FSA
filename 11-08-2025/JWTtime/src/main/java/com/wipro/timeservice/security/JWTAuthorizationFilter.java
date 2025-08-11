package com.wipro.timeservice.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "timeServiceSecretKey";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			System.out.println("JWT Filter - Request URI: " + request.getRequestURI());
			System.out.println("JWT Filter - Authorization Header: " + request.getHeader(HEADER));
			
			// Skip JWT validation for login endpoint
			if ("/login".equals(request.getRequestURI())) {
				System.out.println("JWT Filter - Skipping JWT validation for login endpoint");
				chain.doFilter(request, response);
				return;
			}
			
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
					System.out.println("JWT Filter - Authentication successful for user: " + claims.getSubject());
				} else {
					System.out.println("JWT Filter - No authorities found in token");
					SecurityContextHolder.clearContext();
				}
			} else {
				System.out.println("JWT Filter - No valid JWT token found");
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		}
		catch (ExpiredJwtException e) {
			System.out.println("JWT Filter - Token expired: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":\"Token expired\"}");
			return;
		}
		catch (UnsupportedJwtException | MalformedJwtException e) {
			System.out.println("JWT Filter - Invalid token: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":\"Invalid token\"}");
			return;
		}
		catch (Exception e) {
			System.out.println("JWT Filter - Unexpected error: " + e.getMessage());
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":\"Authentication failed\"}");
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		System.out.println("JWT Filter - Extracted token: " + jwtToken.substring(0, Math.min(50, jwtToken.length())) + "...");
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
		System.out.println("JWT Filter - Token validated successfully for subject: " + claims.getSubject());
		return claims;
	}

	/**
	 * Authentication method in Spring flow
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");
		
		System.out.println("JWT Filter - Setting up authentication for: " + claims.getSubject());
		System.out.println("JWT Filter - Authorities: " + authorities);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
			claims.getSubject(), 
			null,
			authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
		System.out.println("JWT Filter - Authentication set in SecurityContext");
	}

	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
 
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}
}
