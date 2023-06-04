
package com.txt.josejwt.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.txt.josejwt.service.JwtService;
import com.txt.josejwt.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (request.getServletPath().contains("swagger-ui")
                || request.getServletPath().contains("swagger-resources")
                || request.getServletPath().contains("v3/api-docs")
                || request.getServletPath().contains("actuator")
                || request.getServletPath().contains("/rest/login")) {

            chain.doFilter(request, response);

        } else {

            String authToken = request.getHeader(JwtService.HEADER_STRING);
            if (StringUtils.isBlank(authToken) || !authToken.startsWith(JwtService.TOKEN_PREFIX)) {
                throw new AuthorizationServiceException("Not authorized");
            }

            String token = authToken.substring(7);
            if (jwtService.validateTokenLogin(token)) {
                String username = jwtService.getUsernameFromToken(token);

                com.txt.josejwt.entities.User user = userService.loadUserByUsername(username);
                if (user != null) {
                    boolean enabled = true;
                    boolean accountNonExpired = true;
                    boolean credentialsNonExpired = true;
                    boolean accountNonLocked = true;
                    UserDetails userDetail = new User(username, user.getPassword(), enabled, accountNonExpired,
                            credentialsNonExpired, accountNonLocked, user.getAuthorities());

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                            null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            chain.doFilter(request, response);
        }
    }
}