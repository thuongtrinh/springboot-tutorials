package com.txt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListenerController {

	@RequestMapping("/app/listener")
	public String helloMsg(HttpServletRequest request) {
		request.getSession(); // this action will active listener of SessionCountListener
		String msg1 = "Admin: " + request.getServletContext().getAttribute("admin");
		String msg2 = "Active Session Count: " + request.getServletContext().getAttribute("activeSessions");
		return msg1 + ", " + msg2;
	}
}
