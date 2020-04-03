package com.txt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.txt.service.MvcAppUserDetailService;

//To secure a method we need to annotate our configuration class by @EnableGlobalMethodSecurity.
//Uncomment @Order(0) to execute

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true) 
//@Order(0)
public class MvcSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private MvcAppUserDetailService userDetailService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/app/secure/**").hasAnyRole("ADMIN", "USER")
			.and().formLogin()
			.loginPage("/app/loginMvc")
			.loginProcessingUrl("/app/login")
			.usernameParameter("app_username")
			.passwordParameter("app_password")
			.defaultSuccessUrl("/app/secure/article-details")
			.and().logout() // logout configuration
			.logoutUrl("/app/logout")
			.logoutSuccessUrl("/app/loginMvc")
			.and().exceptionHandling() // exception handling configuration
			.accessDeniedPage("/403");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
}
