package com.example.demo;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private final SecretKey key = Keys.hmacShaKeyFor(
        "01234567890123456789012345678901"
        .getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {

        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 3600000))
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}