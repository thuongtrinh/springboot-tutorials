package com.txt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//		return new HttpSessionEventPublisher();
//	}

//	@Autowired
//	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("def").password("123456").roles("USER");
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
			.withUser("smith").password("$2a$10$JwATmL/k0dBJCMmW/rLwBOlja.0ppOJvMEdhjx2fQ9qgeXxAb./AW").roles("ADMIN");
		
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
			.withUser("alice").password("$2a$10$JwATmL/k0dBJCMmW/rLwBOlja.0ppOJvMEdhjx2fQ9qgeXxAb./AW").roles("USER");

		/*auth.inMemoryAuthentication()
			.withUser("smith").password("{noop}123456").roles("ADMIN")
			.and()
			.withUser("alice").password("{noop}123456").roles("USER");*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionFixation().newSession()
			.invalidSessionUrl("/login2?message=timeout")
			.maximumSessions(1).expiredUrl("/login2?message=max_session").maxSessionsPreventsLogin(false);

		//http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		//http.authorizeRequests().antMatchers("/admin/**").authenticated();

		http.antMatcher("/admin/**").authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().and().formLogin()
			.loginPage("/login2")
			.loginProcessingUrl("/admin/login")
			.defaultSuccessUrl("/admin/admin")
			.failureUrl("/login2?message=error")
//			.failureHandler(customAuthenticationFailureHandler)
			.usernameParameter("username")
			.passwordParameter("password")
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/login2?message=logout");
	}
}
