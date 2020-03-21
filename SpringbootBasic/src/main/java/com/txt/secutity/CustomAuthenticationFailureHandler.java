package com.txt.secutity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// Login failed by max session
		if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {
			response.sendRedirect(request.getContextPath() + "/login2?message=max_session");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/login2?message=error");
	}

}
