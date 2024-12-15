package org.telran.bank.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.token.signing.key}") String jwtSecretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User user) {
            claims.put("userId", user);
            claims.put("username", user.getUsername());
            claims.put("role", user.getAuthorities());
        }
        return generateToken(userDetails, claims);
    }


    private String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 86400)))
                .signWith(secretKey)
                .compact();

    }

    public String extractUserName(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        String userName = extractUserName(jwt);
        // userName, Expired
        return userDetails.getUsername().equals(userName) &&
                !isExpired(jwt);
    }

    private boolean isExpired(String jwt) {
        try {
            return extractExpiration(jwt).before(new Date());
        } catch (JwtException e) {
            return true;
        }
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        try {
            Claims claims = extractAllClaims(token);
            return claimResolver.apply(claims);
        } catch (JwtException e) {
            throw new IllegalStateException("JWT is invalid or expired", e);
        }
    }

    //Extract all data from token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getBody();
    }
}

