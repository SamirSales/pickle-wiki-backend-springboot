package io.github.samirsales.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    public final static String TOKEN_KEY = "security_key_pickle_wiki";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String header = req.getHeader("Authorization");

        if( header == null || !header.startsWith("Bearer ")){
            throw new ServletException("Invalid token.");
        }

        // Extracting token without bearer
        String token = header.substring(7);

        try{
            Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody();
        }catch (SignatureException ex){
            throw new ServletException("Invalid token.");
        }

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Expose-Headers", "Location");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
