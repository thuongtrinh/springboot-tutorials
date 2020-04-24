package com.txt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String hello(Model model) {
		return "welcome";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model) {
		return "error";
	}
}
