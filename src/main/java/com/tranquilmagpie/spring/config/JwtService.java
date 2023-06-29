package com.tranquilmagpie.spring.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private static final String SIGNING_KEY = System.getenv("TRANQUIL_MAGPIE_SK");

    public String extractUsername(String jwt) {
        return "WIP - TBC";
    }

    private Claims extractAllClaims(String jwt) {
        // Signing key (secret) signs the JWT and used to verify the signature
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwt).getBody();
    }

    private Key getSigningKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyAsBytes);
    }
}
