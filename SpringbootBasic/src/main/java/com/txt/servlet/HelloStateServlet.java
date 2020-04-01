package com.txt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Can use @WebServlet to replace ServletRegistrationBean
//@WebServlet(urlPatterns = "/app/state/*", loadOnStartup = 1)
public class HelloStateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		req.getSession();
		out.println("<h3>Hello State Servlet!</h3>");
		out.println("Admin: " + req.getServletContext().getAttribute("admin"));
		out.println("<br/>Total Active Session: " + req.getServletContext().getAttribute("activeSessions"));
	}
}
