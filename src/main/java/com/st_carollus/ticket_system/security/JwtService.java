package com.st_carollus.ticket_system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.ticket-system.jwt.jwt-secret}")
    private String jwtSecret;

    @Value("${app.ticket-system.jwt.expired}")
    private long jwtExpirationSeconds;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationSeconds * 1000);

        UserPrincipal principal = (UserPrincipal) userDetails;

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roleCode", principal.getRoleCode())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getKey(jwtSecret))
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(parseToken(token), Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(parseToken(token), Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(parseToken(token));
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey(jwtSecret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(parseToken(token)).before(new Date());
    }

    private SecretKey getKey(String sKey){
        byte[] keyBytes = Decoders.BASE64URL.decode(sKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String parseToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
