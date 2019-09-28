package io.github.samirsales.Controller;

import io.github.samirsales.Security.JWTUtil;
import io.github.samirsales.Security.UserSS;
import io.github.samirsales.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){

        UserSS userSS = UserService.authenticated();
        String token = jwtUtil.generateToken(userSS.getUsername());
        response.addHeader("Authorization", "Bearer "+token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

}
