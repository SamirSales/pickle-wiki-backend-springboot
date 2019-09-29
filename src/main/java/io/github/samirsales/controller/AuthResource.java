package io.github.samirsales.controller;

import io.github.samirsales.security.JWTUtil;
import io.github.samirsales.security.UserSecurity;
import io.github.samirsales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
@SuppressWarnings("unused")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSecurity userSecurity = UserService.authenticated();

        if(userSecurity != null){
            String token = jwtUtil.generateToken(userSecurity.getUsername());
            response.addHeader("Authorization", "Bearer "+token);
            response.addHeader("access-control-expose-headers", "Authorization");
        }

        return ResponseEntity.noContent().build();
    }

}
