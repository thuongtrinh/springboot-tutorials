package com.txt.josejwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.txt.josejwt.config.handle.CustomAccessDeniedHandler;
import com.txt.josejwt.filter.JwtAuthenticationTokenFilter;
import com.txt.josejwt.config.handle.RestAuthenticationEntryPoint;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/rest/**");

        http.authorizeRequests().antMatchers("/rest/login**").permitAll();

        http.antMatcher("/rest/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/rest/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/rest/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/rest/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

        return http.build();
    }
}
