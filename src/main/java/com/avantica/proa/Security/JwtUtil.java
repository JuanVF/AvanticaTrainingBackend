package com.avantica.proa.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.avantica.proa.Security.SecurityConstants.*;
import static java.util.Collections.emptyList;

public class JwtUtil {

    public static void addAuthentication(HttpServletResponse res,String email) throws IOException {
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(
                "{\"" + AUTHORIZATION_HEADER + "\":\"" + TOKEN_PREFIX+token + "\"}"
        );
    }

    public static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader(AUTHORIZATION_HEADER);
        if(token != null){
            if(isTokenExpired(token)) return null;

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

    private static boolean isTokenExpired(String token){
        Claims decodedToken = decodeJWT(token);

        Date expirationDate = decodedToken.getExpiration();
        Date currentDate = new Date();

        if(expirationDate.after(currentDate)){
            return false;
        }

        if(expirationDate.before(currentDate)){
            return true;
        }

        if(expirationDate.equals(currentDate)){
            return false;
        }

        return true;
    }

    private static Claims decodeJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt.replace(TOKEN_PREFIX,"")).getBody();

        return claims;
    }
}
