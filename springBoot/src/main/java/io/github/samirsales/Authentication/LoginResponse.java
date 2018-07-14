package io.github.samirsales.Authentication;

import io.github.samirsales.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class LoginResponse {

    private String token;
    private User user;

    public LoginResponse(User user){
        this.user = user;

        token = Jwts.builder()
                .setSubject(user.getLogin())
                .signWith(SignatureAlgorithm.HS512, TokenFilter.TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // 10 minutes of session expiration
                .compact();
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
