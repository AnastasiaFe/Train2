package ua.nure.fedorenko.kidstim.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

class TokenAuthenticationService {

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();

        System.out.println(JWT);
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            String user;
            try {
                user = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            } catch (ExpiredJwtException ex) {
                return null;
            }
        }
        return null;
    }
}