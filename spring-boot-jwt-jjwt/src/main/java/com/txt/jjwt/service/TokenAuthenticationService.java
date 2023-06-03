package com.txt.jjwt.service;

import static java.util.Collections.emptyList;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static String addAuthentication(HttpServletResponse res, String username) {
        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //SignatureAlgorithm.HS512

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + EXPIRATIONTIME);

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        String JWT = Jwts.builder()
                //.setId(id)
                //.setIssuer(issuer)
                .setIssuedAt(now)
                .setSubject(username)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, signingKey)
                .compact(); //Builds the JWT and serializes it to a compact, URL-safe string

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        return JWT;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (!StringUtils.isEmpty(token)) {
            // parse the token.
			/*String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();*/
            String user = decodeJWT(token).getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }

        return null;
    }

    public static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt.substring(7))
                .getBody();

        return claims;
    }
}
