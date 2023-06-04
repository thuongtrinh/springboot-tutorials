package com.txt.jjwt.filters;

import com.txt.jjwt.utils.JWTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("swagger-ui")
                || request.getServletPath().contains("swagger-resources")
                || request.getServletPath().contains("v3/api-docs")
                || request.getServletPath().contains("actuator")
                || request.getServletPath().contains("/restjjwt/login")) {

            filterChain.doFilter(request, response);

        } else {
            try {
                String authHeader = request.getHeader(JWTokenUtils.HEADER_STRING);
                log.debug(String.format("doFilterInternal - Header: %s", authHeader));
                if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JWTokenUtils.TOKEN_PREFIX)) {
                    throw new AuthorizationServiceException("Not authorized");
                }

                String token = authHeader.substring(7);
                if (StringUtils.isNotBlank(token)) {
                    Claims claims = JWTokenUtils.decodeJWT(token);
                    List<String> rolesMap = claims.get("role", List.class);
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    if (rolesMap != null) {
                        for (String rolemap : rolesMap) {
                            authorities.add(new SimpleGrantedAuthority(rolemap));
                        }
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("jwt.getClaims() is null");
                throw new AuthorizationServiceException("Unidentified preferred_username, please check authentication provider!");
            }

            if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
                filterChain.doFilter(request, response);
            }
        }
    }
}
