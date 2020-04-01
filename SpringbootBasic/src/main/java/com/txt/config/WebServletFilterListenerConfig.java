package com.txt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.txt.filter.A1ServletFilter;
import com.txt.filter.A2ServletFilter;
import com.txt.listener.AdminInfoListener;
import com.txt.listener.SessionCountListener;
import com.txt.servlet.HelloCountryServlet;
import com.txt.servlet.HelloStateServlet;

/*
I. SpringBoot with filter: creating filters has three ways:
	1. Register Filter with FilterRegistrationBean
	2. Register Filter with @Component and @Order
	3. Register Filter with @ServletComponentScan and @WebFilter

II. SpringBoot servlet mapping 
	1. Registering Servlets as Spring Beans using ServletRegistrationBean
	2. Scanning for Servlets using @ServletComponentScan and @WebServlet

III. Spring Boot Listener
	1. Register Listener with ServletListenerRegistrationBean
	2. Register Listener with @Component
	3. Register Listener with @ServletComponentScan and @WebListener
*/
@Configuration
public class WebServletFilterListenerConfig {

	// Register A1ServletFilter
	@Bean
	public FilterRegistrationBean<A1ServletFilter> a1ServletFilter() {
		FilterRegistrationBean<A1ServletFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new A1ServletFilter());
		filterRegBean.addUrlPatterns("/app/*");
		filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
		return filterRegBean;
	}

	// Register A2ServletFilter
	@Bean
	public FilterRegistrationBean<A2ServletFilter> a2ServletFilter() {
		FilterRegistrationBean<A2ServletFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new A2ServletFilter());
		filterRegBean.addUrlPatterns("/app/*");
		filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
		return filterRegBean;
	}

	// Register HelloCountryServlet
	@Bean
	public ServletRegistrationBean<HelloCountryServlet> countryServlet() {
		ServletRegistrationBean<HelloCountryServlet> servRegBean = new ServletRegistrationBean<>();
		servRegBean.setServlet(new HelloCountryServlet());
		servRegBean.addUrlMappings("/app/country/*");
		servRegBean.setLoadOnStartup(1);
		return servRegBean;
	}

	// Register HelloStateServlet
	@Bean
	public ServletRegistrationBean<HelloStateServlet> stateServlet() {
		ServletRegistrationBean<HelloStateServlet> servRegBean = new ServletRegistrationBean<>();
		servRegBean.setServlet(new HelloStateServlet());
		servRegBean.addUrlMappings("/app/state/*");
		servRegBean.setLoadOnStartup(1);
		return servRegBean;
	}

/*
	// Register SessionCountListener
	@Bean
	public ServletListenerRegistrationBean<SessionCountListener> sessionCountListener() {
		ServletListenerRegistrationBean<SessionCountListener> listenerRegBean = new ServletListenerRegistrationBean<>();
		listenerRegBean.setListener(new SessionCountListener());
		return listenerRegBean;
	}

	// Register AdminInfoListener
	@Bean
	public ServletListenerRegistrationBean<AdminInfoListener> adminInfoListener() {
		ServletListenerRegistrationBean<AdminInfoListener> listenerRegBean = new ServletListenerRegistrationBean<>();
		listenerRegBean.setListener(new AdminInfoListener());
		return listenerRegBean;
	}
*/
}
