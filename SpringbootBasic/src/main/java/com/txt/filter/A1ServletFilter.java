package com.txt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//Can register Filter with @Component and @Order replace for FilterRegistrationBean
//@Order(Ordered.LOWEST_PRECEDENCE -2)
//@Component
public class A1ServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("Inside A1ServletFilter: " + req.getRequestURI());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
