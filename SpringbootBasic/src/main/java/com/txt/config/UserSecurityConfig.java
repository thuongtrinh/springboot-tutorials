package com.txt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// config for form login user
		http.authorizeRequests().antMatchers("/user/**").hasRole("USER")
			.and().formLogin()
			.loginProcessingUrl("/j_spring_security_login")
			.loginPage("/login1")
			.defaultSuccessUrl("/user")
			.failureUrl("/login1?message=error")//
			.usernameParameter("username").passwordParameter("password")
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login1?message=logout");

		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
	}
}
