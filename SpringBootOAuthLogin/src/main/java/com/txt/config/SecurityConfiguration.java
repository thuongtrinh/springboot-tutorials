package com.txt.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login**", "/error**").permitAll().anyRequest().authenticated()
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
			.and().oauth2Login();
	}

	/*@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = new ArrayList<>();
		//registrations.add(githubClientRegistration());
		registrations.add(googleClientRegistration());
		return new InMemoryClientRegistrationRepository(registrations);
	}*/

	/*private ClientRegistration githubClientRegistration() {
		return ClientRegistration.withRegistrationId("github").clientId("github-client-id")
				.clientSecret("github-client-secret").clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
				.authorizationUri("https://github.com/login/oauth/authorize")
				.tokenUri("https://github.com/login/oauth/access_token").userInfoUri("https://api.github.com/user")
				.clientName("GitHub").build();
	}*/

	/*private ClientRegistration googleClientRegistration() {
		return ClientRegistration.withRegistrationId("google").clientId("google-client-id")
				.clientSecret("google-client-secret").clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")  // http://localhost:8080/OAuthLoginDemo/login/oauth2/code/google
				.scope("openid", "profile", "email", "address", "phone")
				.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
				.tokenUri("https://www.googleapis.com/oauth2/v4/token")
				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
				.userNameAttributeName(IdTokenClaimNames.SUB).jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
				.clientName("Google").build();
	}*/
}
