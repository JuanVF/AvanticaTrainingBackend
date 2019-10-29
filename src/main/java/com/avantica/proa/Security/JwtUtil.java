package com.avantica.proa.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static com.avantica.proa.Security.SecurityConstants.*;
import static java.util.Collections.emptyList;

public class JwtUtil {

    static void addAuthentication(HttpServletResponse res,String email){
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

    static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader(AUTHORIZATION_HEADER);

        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user,null,emptyList()) :
                    null;
        }
        return null;
    }
}
