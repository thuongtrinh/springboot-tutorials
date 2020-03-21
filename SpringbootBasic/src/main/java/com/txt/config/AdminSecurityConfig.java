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

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder(){
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}

//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//		return new HttpSessionEventPublisher();
//	}

//	@Autowired
//	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("abc").password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("def").password("123456").roles("USER");
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

		auth.inMemoryAuthentication()
			.withUser("thuongtx").password("{noop}123456").roles("USER")
			.and()
			.withUser("tungtx").password("{noop}123456").roles("ADMIN");
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
			.loginProcessingUrl("/admin/j_spring_security_login")
			.defaultSuccessUrl("/admin/admin")
			.failureUrl("/login2?message=error")
//			.failureHandler(customAuthenticationFailureHandler)
			.usernameParameter("username")
			.passwordParameter("password")
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().logout().logoutUrl("/admin/j_spring_security_logout").logoutSuccessUrl("/login2?message=logout");
	}
}
