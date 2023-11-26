package com.txt.security.registration.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txt.security.registration.dto.authen.SecurityAuthority;
import com.txt.security.registration.exception.InvalidCredentialException;
import com.txt.security.registration.service.UserService;
import com.txt.security.registration.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isByPassUrl(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String username = JwtUtil.getEmail(request, "preferred_username");
            List<String> roles = new ArrayList<>(userService.getUserRolesByUsername(username));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                    (username, null, setupSecurityAuthority(roles));
            log.debug("doFilterInternal - Username : {} - Roles: {}", username, objectMapper.writeValueAsString(roles));
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (InvalidCredentialException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        }
    }

    private List<SecurityAuthority> setupSecurityAuthority(List<String> roles) {
        List<SecurityAuthority> securityAuthorityList = new ArrayList<>();
        for (String role : roles) {
            securityAuthorityList.add(new SecurityAuthority(role));
        }
        return securityAuthorityList;
    }

    private boolean isByPassUrl(String url) {
        return url.contains("swagger-ui")
                || url.contains("swagger-resources")
                || url.contains("v3/api-docs")
                || url.contains("actuator")
                || url.contains("/api/v1/auth/login")
                || url.contains("/api/v1/auth/registration")
                || url.contains("/api/v1/auth/registration-confirm")
                || url.contains("/api/v1/auth/resend-registration-token");
    }
}
