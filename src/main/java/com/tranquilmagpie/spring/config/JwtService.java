package com.tranquilmagpie.spring.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    
    private final String SECRET_KEY;

    public JwtService(@Value("${tranquil.magpie.sk}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    // Generate token without extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(Collections.emptyMap(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                // Generate and return JWT
                .compact();
    }

    public String revokeToken(UserDetails userDetails) {
        // TODO: is this required
        return "WIP - TBC";
    }

    private Key getSigningKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Generate a signing key (HMAC-SHA code) to authenticate data
        return Keys.hmacShaKeyFor(keyAsBytes);
    }

    public String extractUsername(String jwt) {
        // Username should be the JWT's subject
        // TODO: review usage of method reference
        return extractOneClaim(jwt, Claims::getSubject);
    }

    private Date extractExpiration(String jwt) {
        return extractOneClaim(jwt, Claims::getExpiration);
    }
    // TODO: review usage of functional interface

    public <T> T extractOneClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        // Signing key (secret) signs the JWT and used to verify the signature
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isJwtValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        // Check username in JWT matches username held in UserDetails
        // Check JWT has not expired
        return username.equals(userDetails.getUsername()) && !isJwtExpired(jwt);
    }

    private boolean isJwtExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

}
