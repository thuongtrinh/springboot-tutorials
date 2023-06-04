package com.txt.jjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

import java.security.Key;
import java.util.Date;
import java.util.Map;


@Component
public class JWTokenUtils {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret123FRFG$fdjdjfdf&^&$kbnkvmbkppp4554#$$D122@55gfppmv";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String addAuthentication(HttpServletResponse res, String username) {
        // The JWT signature algorithm we will be using to sign the token
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + EXPIRATIONTIME);
        Map<String, Object> claims;

        //Let's set the JWT Claims
        String JWT = Jwts.builder()
                //.setId(id)
                //.setIssuer(issuer)
                //.setClaims(claims)
                .setIssuedAt(now)
                .setSubject(username)
                .setExpiration(exp)
                .signWith(key)
                .compact(); //Builds the JWT and serializes it to a compact, URL-safe string

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        return JWT;
    }

    public Claims decodeJWT(String token) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (!StringUtils.isEmpty(token)) {
            // parse the token.
            String user = decodeJWT(token).getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }

        return null;
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = decodeJWT(token).getExpiration();
        return expiration.before(new Date());
    }

}
