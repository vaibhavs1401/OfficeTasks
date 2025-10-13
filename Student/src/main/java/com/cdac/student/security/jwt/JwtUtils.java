package com.cdac.student.security.jwt;

import com.cdac.student.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    // At least 32+ characters for HS256
    private final String jwtSecret = "CHANGE_ME_TO_A_LONG_RANDOM_SECRET_at_least_32_chars";
    private final int jwtExpirationMs = 3600_000; // 1 hour

    private final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    public String generateToken(UserAccount principal) {
        return Jwts.builder()
                .setSubject(principal.getUsername())        // email
                .claim("roles", principal.getAuthorities())
                .claim("id", principal.getId())// optional
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    public boolean validate(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
