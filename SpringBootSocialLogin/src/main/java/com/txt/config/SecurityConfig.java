package com.txt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("thuongtx").password("{noop}123456").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tungtx").password("{noop}123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login**").permitAll();

		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests()
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/user")
			.failureUrl("/login?message=error")
			.usernameParameter("username")//
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?message=logout");
	}
}
