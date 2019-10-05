package io.github.samirsales.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.samirsales.util.TextEncryption;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    @SuppressWarnings("WeakerAccess")
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        try{
            CredentialsDTO credentialsDTO = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class);
            TextEncryption textEncryption = new TextEncryption();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    credentialsDTO.getLogin(),
                    textEncryption.getMD5(credentialsDTO.getPassword()),
                    new ArrayList<>());

            return authenticationManager.authenticate(authToken);
        }catch (IOException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String username = ((UserSecurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }
}
