package com.tranquilmagpie.spring.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = System.getenv("TRANQUIL_MAGPIE_SK");

    public String extractUsername(String jwt) {
        // Username should be the JWT's subject
        // TODO: review usage of method reference
        return extractOneClaim(jwt, Claims::getSubject);
    }

    // TODO: review usage of functional interface
    public <T> T extractOneClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    // Generate token without extra claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                // Generate and return JWT
                .compact();
    }

    private boolean isJWTValid(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isJWTExpired(jwt);
    }

    private boolean isJWTExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractOneClaim(jwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwt) {
        // Signing key (secret) signs the JWT and used to verify the signature
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Generate a signing key (HMAC-SHA code) to authenticate data
        return Keys.hmacShaKeyFor(keyAsBytes);
    }
}
