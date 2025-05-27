package com.example.demo.service;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
	 @Value("${JWT_SECRET}")
	    private String secretCode;

	    private SecretKey jwtSecret;
	    private final long jwtExpirationMs = 86400000; // 1 day

	    @PostConstruct
	    public void init() {
	        if (secretCode == null || secretCode.trim().isEmpty()) {
	            throw new IllegalStateException("JWT_SECRET must be configured in application.properties");
	        }

	        this.jwtSecret = Keys.hmacShaKeyFor(secretCode.getBytes(StandardCharsets.UTF_8));
	    }

	    public String generateJwtToken(Usuario usuario) {
	        return Jwts.builder()
	                .setSubject(String.valueOf(usuario.getUsua_id()))
	                .claim("rol", String.valueOf(usuario.getRol_rol_id()))
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	                .signWith(jwtSecret, SignatureAlgorithm.HS512)
	                .compact();
	    }

	    public boolean validateJwtToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                    .setSigningKey(jwtSecret)
	                    .build()
	                    .parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public String extractToken(String authHeader) {
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            return authHeader.substring(7);
	        }
	        return null;
	    }

	    public Integer getIdFromToken(String token) {
	        try {
	            Claims claims = Jwts.parserBuilder()
	                    .setSigningKey(jwtSecret)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();
	           return Integer.valueOf(claims.getSubject());
	        } catch (Exception e) {
	            return null;
	        }
	    }

	    public String getRolFromToken(String token) {
	        try {
	            Claims claims = Jwts.parserBuilder()
	                    .setSigningKey(jwtSecret)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();
	            return claims.get("rol", String.class);
	        } catch (Exception e) {
	            return null;
	        }
	    }
}
