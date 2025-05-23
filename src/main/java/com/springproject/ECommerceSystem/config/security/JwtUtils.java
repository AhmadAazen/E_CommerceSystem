package com.springproject.ECommerceSystem.config.security;

import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
@Component
public class JwtUtils {
	private final String jwtSecret = "YourSecretKey";
	private final long jwtExpirationMs = 86400000;
	
	public String generateJwtToken(UserDetails userDetails) {
		 return Jwts.builder()
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();			
	}
	public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	 public boolean validateJwtToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            // log error
	        }
	        return false;
	    }
}
