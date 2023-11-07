package com.txt.security.registration.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.txt.security.registration.exception.InvalidCredentialException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

@Slf4j
public class JwtUtil {
    private static final String USER_IDENTIFIER_KEY = "preferred_username";

    public static String getEmail(HttpServletRequest request, String userIdentifyKey) {
        if (ObjectUtils.isEmpty(request)) {
            throw new InvalidCredentialException("getEmail::Invalid request");
        }
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(tokenHeader)) {
            throw new InvalidCredentialException("Token is missing");
        }
        String token = tokenHeader.replace("Bearer ", "");
        DecodedJWT jwt = JWT.decode(token);
        log.debug("doFilterInternal - Token decryption : {}", jwt.getClaims());
        Claim claim = jwt.getClaims().get(StringUtils.defaultString(userIdentifyKey, USER_IDENTIFIER_KEY));
        String email = claim.asString();

        if (StringUtils.isBlank(email)) {
            throw new InvalidCredentialException("Invalid token cannot get email from token by key " + userIdentifyKey);
        }
        return email;
    }

    public static String getEmail(String tokenHeader) {
        if (StringUtils.isBlank(tokenHeader)) {
            throw new InvalidCredentialException("Token is missing");
        }
        String token = tokenHeader.replace("Bearer ", "");
        DecodedJWT jwt = JWT.decode(token);
        log.debug("doFilterInternal - Token decryption : {}", jwt.getClaims());
        Claim claim = jwt.getClaims().get(USER_IDENTIFIER_KEY);
        String email = claim.asString();

        if (StringUtils.isBlank(email)) {
            throw new InvalidCredentialException("Invalid token :: Cannot get email from token");
        }
        return email;
    }

    private JwtUtil() {

    }
}
