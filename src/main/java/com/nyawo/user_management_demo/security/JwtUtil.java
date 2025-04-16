package com.nyawo.user_management_demo.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;


@Component
public class JwtUtil {
    
    private String secrete ="Celenkosini@1234";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secrete)
                .compact();
    }   

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secrete).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username));
    }
}
