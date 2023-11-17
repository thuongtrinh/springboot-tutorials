package com.txt.security.registration.config;

import com.txt.security.registration.common.RoleConstant;
import com.txt.security.registration.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html");
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("api/v1/**")
//                .hasAnyAuthority(RoleConstant.allowAccessProcessInstanceApiRoles())
                .requestMatchers("/swagger-resources/**", "/swagger-ui/**",
                        "/v3/api-docs/**", "/swagger-ui.html", "/actuator/**",
                        "/api/v1/auth/**"//,
//                        "/api/v1/auth/registration-confirm", "/api/v1/auth/user/reset-password",
//                        "/api/v1/auth/user/resend-registration-token"
                )
                .permitAll()
                .anyRequest().permitAll());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
