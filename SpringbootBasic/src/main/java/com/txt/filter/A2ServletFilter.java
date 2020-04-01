package com.txt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//Can register Filter with @Component and @Order to replace for ServletRegistrationBean
//@Order(Ordered.LOWEST_PRECEDENCE -1)
//@Component
public class A2ServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("Inside A2ServletFilter: " + req.getRequestURI());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
