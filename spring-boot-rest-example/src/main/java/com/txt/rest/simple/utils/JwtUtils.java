package com.txt.rest.simple.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtils {

    public static String getJWTToken(String bearerToken) {
        String jwtToken = bearerToken.replace("Bearer ", "");
        return jwtToken;
    }

    public static String getUsername(String bearerToken) {
        DecodedJWT jwt = JWT.decode(getJWTToken(bearerToken));
        log.info("Get claim - Token decryption: {}", jwt.getClaims());
        Claim mailClaim = jwt.getClaims().get("preferred_username");
        return mailClaim != null ? mailClaim.asString() : null;
    }

}
