package io.github.samirsales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    @SuppressWarnings("unused")
    private String secret;

    @Value("${jwt.expirationTimeInHours}")
    @SuppressWarnings("unused")
    private Long expirationTimeInHours;

    public String generateToken(String username){
        long expirationTimeInMilliseconds = expirationTimeInHours * 1000 * 60 * 60;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMilliseconds);

        return Jwts.builder().setSubject(username)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret.getBytes())
            .compact();
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if(claims != null){
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            return userName != null && expirationDate != null && now.before(expirationDate);
        }

        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception ex){
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);

        if(claims != null){
            return claims.getSubject();
        }

        return null;
    }
}
